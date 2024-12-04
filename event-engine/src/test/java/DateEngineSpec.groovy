import com.example.recommend.event.engine.config.KafkaProducer
import com.example.recommend.event.engine.dto.DataRequest
import com.example.recommend.event.engine.engine.DataEngine
import com.example.recommend.event.engine.engine.DataEngineConfig
import com.example.recommend.event.engine.engine.DataEngineInstance
import com.example.recommend.event.engine.engine.fetcher.DataFetcher
import com.example.recommend.event.engine.engine.fetcher.FetchEngine
import com.example.recommend.event.engine.engine.fetcher.impl.ApiFetcher
import com.example.recommend.event.engine.engine.fetcher.impl.PageFetcher
import com.example.recommend.event.engine.engine.parser.DataParser
import com.example.recommend.event.engine.engine.parser.ParseEngine
import com.example.recommend.event.engine.engine.parser.html.PeoplesDailyPageParser
import com.example.recommend.event.engine.engine.parser.html.SohuPageParser
import com.example.recommend.event.engine.engine.parser.json.StockJsonParser
import com.example.recommend.event.engine.engine.store.StoreEngine
import com.example.recommend.event.engine.engine.store.impl.KafkaStore
import com.example.recommend.event.engine.engine.store.impl.MySqlStore
import com.example.recommend.event.engine.enums.FetchTypeEnum
import com.example.recommend.event.engine.enums.ParseTypeEnum
import com.example.recommend.event.engine.enums.SourceTypeEnum
import com.example.recommend.event.engine.enums.StoreTypeEnum
import com.google.common.collect.Maps
import edu.uci.ics.crawler4j.crawler.Page
import edu.uci.ics.crawler4j.parser.HtmlParseData
import edu.uci.ics.crawler4j.url.WebURL
import org.apache.http.HttpStatus
import org.springframework.kafka.core.KafkaTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

/**
 * @author:jycx
 * @date: 2024/12/3 21:42
 */
@Subject(DataEngineConfig)
class DateEngineSpec extends Specification {

    @Shared
    DataEngine dataEngine = new DataEngineInstance(
            fetchEngine: new FetchEngine(fetcherMap: new HashMap<FetchTypeEnum, DataFetcher>() {
                {
                    put(FetchTypeEnum.CRAWLER, new PageFetcher())
                    put(FetchTypeEnum.API, new ApiFetcher())
                }
            }),
            parseEngine: new ParseEngine(parserMap: new HashMap<ParseTypeEnum, DataParser>() {
                {
                    put(ParseTypeEnum.SO_HU, new SohuPageParser())
                    put(ParseTypeEnum.PEOPLES_DAILY, new PeoplesDailyPageParser())
                    put(ParseTypeEnum.STOCK, new StockJsonParser())
                }
            }),
            storeEngine: new StoreEngine(storeMap: Maps.newHashMap())
    )

    @Unroll
    def "dateEngine-process-method-test"() {
        given: "环境初始化"
        dataEngine.storeEngine.storeMap.put(StoreTypeEnum.KAFKA, new KafkaStore(
                kafkaProducer: new KafkaProducer(kafkaTemplate: Stub(KafkaTemplate))
        ))
        dataEngine.storeEngine.storeMap.put(StoreTypeEnum.MY_SQL, new MySqlStore())

        and: "入参准备"
        DataRequest req = new DataRequest<>(sourceType: sourceType, payload: payload)

        when: "执行"
        dataEngine.process(req)

        then:
        noExceptionThrown()

        where:
        sourceType                   | payload
        SourceTypeEnum.SO_HU         | buildPageObj(DataEngineConfig.SOURCE_URI_CONFIG.get(SourceTypeEnum.SO_HU))
        SourceTypeEnum.PEOPLES_DAILY | buildPageObj(DataEngineConfig.SOURCE_URI_CONFIG.get(SourceTypeEnum.PEOPLES_DAILY))
        SourceTypeEnum.STOCK         | "{\"title\":\"股市资讯\",\"keywords\":[\"股市\",\"资讯\",\"波动\"],\"url\":\"https://www.baidu.com/\",\"content\":\"XXX等多只股票股票正在快速拉升\",\"author\":\"金融资讯网\",\"datePublished\":\"2024-12-03 12:01:05\"}"
    }

    private Page buildPageObj(String url) {
        WebURL webURL = new WebURL()
        webURL.setURL(url)
        Page page = new Page(webURL)
        page.setStatusCode(HttpStatus.SC_OK)
        String pageStr = "<html>\n" + "                    <head>\n" + "                        <title>全国交通安全日丨冬季行车安全指南_吉林省_车辆_路面</title>\n" + "                        <meta name=\"keywords\" content=\"冰雪,天气,车辆,轮胎,路面,车主,吉林省,行车,指南,雪天,冰雪,路面,敦化市,雪地胎,吉林省\" />\n" + "                        <meta name=\"author\" content=\"搜狐新闻\" />\n" + "                        <meta itemprop=\"datePublished\" content=\"2024-12-03 10:44\" />\n" + "                    </head>\n" + "                    <body>\n" + "                        <article class=\"article\" id=\"mp-editor\">\n" + "                        近段时间，吉林省迎来雨雪降温及寒潮天气，一些地段出现雪冰混合路面，为交通出行造成一定影响......\n" + "                        </article>\n" + "                    </body>\n" + "                </html>"
        HtmlParseData parseData = new HtmlParseData()
        parseData.setHtml(pageStr)
        page.setParseData(parseData)
        return page
    }
}