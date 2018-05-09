
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class MapOverview extends Simulation {

	val httpProtocol = http
		.baseURL("http://192.168.99.100:8082")
		.proxy(Proxy("192.168.99.100", 8082).httpsPort(8082))
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.117 Safari/537.36")

	val headers0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers1 = Map(
		"Accept" -> "text/css,*/*;q=0.1",
		"Proxy-Connection" -> "keep-alive")

	val headers2 = Map(
		"Accept" -> "image/webp,image/apng,image/*,*/*;q=0.8",
		"Proxy-Connection" -> "keep-alive")

	val headers3 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Origin" -> "http://192.168.99.100:8082",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers5 = Map("Proxy-Connection" -> "keep-alive")

	val headers11 = Map(
		"Origin" -> "http://192.168.99.100:8082",
		"Proxy-Connection" -> "keep-alive")

	val headers12 = Map(
		"Content-type" -> "text/plain",
		"Origin" -> "http://192.168.99.100:8082",
		"Proxy-Connection" -> "keep-alive")

	val headers17 = Map(
		"Accept" -> "text/event-stream",
		"Proxy-Connection" -> "keep-alive")



	val scn = scenario("MapOverview")
		.exec(http("request_0")
			.get("/")
			.headers(headers0)
			.resources(http("request_1")
			.get("/css/custom.css")
			.headers(headers1),
            http("request_2")
			.get("/favicon.ico")
			.headers(headers2)
			.check(status.is(500))))
		.pause(10)
		.exec(http("request_3")
			.post("/login")
			.headers(headers3)
			.formParam("username", "operator1@dashboard.com")
			.formParam("password", "123456")
			.resources(http("request_4")
			.get("/css/incidetails.css")
			.headers(headers1),
            http("request_5")
			.get("/script/incidents_update.js")
			.headers(headers5),
            http("request_6")
			.get("/css/custom.css")
			.headers(headers1),
            http("request_7")
			.get("/dashboard/info?t=1525011899974")
			.headers(headers5),
            http("request_8")
			.get("/favicon.ico")
			.headers(headers2)))
		.pause(1)
		.exec(http("request_9")
			.get("/dashboard/iframe.html")
			.headers(headers0))
		.pause(1)
		.exec(http("request_10")
			.get("/dashboard/iframe.html")
			.headers(headers0)
			.resources(http("request_11")
			.post("/dashboard/040/s1nnlvys/xhr?t=1525011904572")
			.headers(headers11),
            http("request_12")
			.post("/dashboard/040/s1nnlvys/xhr_send?t=1525011904587")
			.headers(headers12)
			.body(RawFileBody("MapOverview_0012_request.txt")),
            http("request_13")
			.post("/dashboard/040/s1nnlvys/xhr?t=1525011904587")
			.headers(headers11),
            http("request_14")
			.post("/dashboard/040/s1nnlvys/xhr_send?t=1525011904638")
			.headers(headers12)
			.body(RawFileBody("MapOverview_0014_request.txt"))))
		.pause(2)
		.exec(http("request_15")
			.get("/dashboard/maps")
			.headers(headers0)
			.resources(http("request_16")
			.get("/dashboard/040/xrylitoc/htmlfile?c=_jp.al5zdjg")
			.headers(headers0),
            http("request_17")
			.get("/dashboard/040/rui31zbq/eventsource")
			.headers(headers17),
            http("request_18")
			.post("/dashboard/040/p0uc5fuv/xhr_streaming?t=1525011900650")
			.headers(headers11),
            http("request_19")
			.get("/script/map_sockets.js")
			.headers(headers5),
            http("request_20")
			.get("/css/map.css")
			.headers(headers1),
            http("request_21")
			.get("/css/custom.css")
			.headers(headers1),
            http("request_22")
			.get("/dashboard/info?t=1525011907929")
			.headers(headers5)))
		.pause(5)
		.exec(http("request_23")
			.get("/dashboard/iframe.html")
			.headers(headers0))
		.pause(2)
		.exec(http("request_24")
			.get("/dashboard/iframe.html")
			.headers(headers0))
		.pause(1)
		.exec(http("request_25")
			.post("/dashboard/811/jpqrzup3/xhr?t=1525011916660")
			.headers(headers11)
			.resources(http("request_26")
			.post("/dashboard/811/jpqrzup3/xhr_send?t=1525011916695")
			.headers(headers12)
			.body(RawFileBody("MapOverview_0026_request.txt")),
            http("request_27")
			.post("/dashboard/811/jpqrzup3/xhr?t=1525011916695")
			.headers(headers11),
            http("request_28")
			.post("/dashboard/811/jpqrzup3/xhr_send?t=1525011916756")
			.headers(headers12)
			.body(RawFileBody("MapOverview_0028_request.txt"))))
		.pause(3)
		.exec(http("request_29")
			.get("/incident/fQQKVyej7B/details")
			.headers(headers0)
			.resources(http("request_30")
			.get("/dashboard/811/n4antbh0/eventsource")
			.headers(headers17),
            http("request_31")
			.post("/dashboard/811/zu5svl5a/xhr_streaming?t=1525011909619")
			.headers(headers11),
            http("request_32")
			.get("/dashboard/811/lfrg43i2/htmlfile?c=_jp.apbtaj1")
			.headers(headers0),
            http("request_33")
			.get("/css/incidetails.css")
			.headers(headers1),
            http("request_34")
			.get("/css/custom.css")
			.headers(headers1)))
		.pause(13)
		.exec(http("request_35")
			.get("/logout")
			.headers(headers0)
			.check(status.is(500)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}