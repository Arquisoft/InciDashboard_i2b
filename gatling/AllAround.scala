
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class AllAround extends Simulation {

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
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Origin" -> "http://192.168.99.100:8082",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers3 = Map("Proxy-Connection" -> "keep-alive")

	val headers9 = Map(
		"Origin" -> "http://192.168.99.100:8082",
		"Proxy-Connection" -> "keep-alive")

	val headers10 = Map(
		"Content-type" -> "text/plain",
		"Origin" -> "http://192.168.99.100:8082",
		"Proxy-Connection" -> "keep-alive")

	val headers14 = Map(
		"Accept" -> "text/event-stream",
		"Proxy-Connection" -> "keep-alive")

    val uri1 = "http://d3js.org"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/")
			.headers(headers0)
			.resources(http("request_1")
			.get("/css/custom.css")
			.headers(headers1)))
		.pause(4)
		.exec(http("request_2")
			.post("/login")
			.headers(headers2)
			.formParam("username", "operator1@dashboard.com")
			.formParam("password", "123456")
			.resources(http("request_3")
			.get("/script/incidents_update.js")
			.headers(headers3),
            http("request_4")
			.get("/css/custom.css")
			.headers(headers1),
            http("request_5")
			.get("/css/incidetails.css")
			.headers(headers1),
            http("request_6")
			.get("/dashboard/info?t=1524930819534")
			.headers(headers3)))
		.pause(2)
		.exec(http("request_7")
			.get("/dashboard/iframe.html")
			.headers(headers0))
		.pause(1)
		.exec(http("request_8")
			.get("/dashboard/iframe.html")
			.headers(headers0))
		.pause(1)
		.exec(http("request_9")
			.post("/dashboard/971/u3pkck5s/xhr?t=1524930824331")
			.headers(headers9)
			.resources(http("request_10")
			.post("/dashboard/971/u3pkck5s/xhr_send?t=1524930824347")
			.headers(headers10)
			.body(RawFileBody("RecordedSimulation_0010_request.txt")),
            http("request_11")
			.post("/dashboard/971/u3pkck5s/xhr?t=1524930824348")
			.headers(headers9),
            http("request_12")
			.post("/dashboard/971/u3pkck5s/xhr_send?t=1524930824401")
			.headers(headers10)
			.body(RawFileBody("RecordedSimulation_0012_request.txt"))))
		.pause(6)
		.exec(http("request_13")
			.get("/dashboard/maps")
			.headers(headers0)
			.resources(http("request_14")
			.get("/dashboard/971/uce4ispi/eventsource")
			.headers(headers14),
            http("request_15")
			.post("/dashboard/971/kzsdnfv3/xhr_streaming?t=1524930820243")
			.headers(headers9),
            http("request_16")
			.get("/dashboard/971/kbha4baa/htmlfile?c=_jp.awzxh01")
			.headers(headers0),
            http("request_17")
			.get("/css/custom.css")
			.headers(headers1),
            http("request_18")
			.get("/css/map.css")
			.headers(headers1),
            http("request_19")
			.get("/script/map_sockets.js")
			.headers(headers3),
            http("request_20")
			.get("/dashboard/info?t=1524930831058")
			.headers(headers3)))
		.pause(3)
		.exec(http("request_21")
			.get("/incident/STiVC4eSNK/details")
			.headers(headers0)
			.resources(http("request_22")
			.post("/dashboard/384/vy4hfcxs/xhr_streaming?t=1524930832724")
			.headers(headers9),
            http("request_23")
			.get("/css/custom.css")
			.headers(headers1),
            http("request_24")
			.get("/css/incidetails.css")
			.headers(headers1)))
		.pause(5)
		.exec(http("request_25")
			.get("/dashboard/maps")
			.headers(headers0)
			.resources(http("request_26")
			.get("/css/custom.css")
			.headers(headers1),
            http("request_27")
			.get("/css/map.css")
			.headers(headers1),
            http("request_28")
			.get("/script/map_sockets.js")
			.headers(headers3),
            http("request_29")
			.get("/dashboard/info?t=1524930840005")
			.headers(headers3)))
		.pause(1)
		.exec(http("request_30")
			.get("/dashboard/charts")
			.headers(headers0)
			.resources(http("request_31")
			.get("/css/custom.css")
			.headers(headers1),
            http("request_32")
			.get(uri1 + "/d3.v3.min.js")
			.headers(headers3),
            http("request_33")
			.get("/script/chart_sockets.js")
			.headers(headers3),
            http("request_34")
			.get("/script/c3.min.js")
			.headers(headers3),
            http("request_35")
			.get(uri1 + "/login")
			.headers(headers3)
			.check(status.is(500)),
            http("request_36")
			.get("/dashboard/info?t=1524930841466")
			.headers(headers3)))
		.pause(1)
		.exec(http("request_37")
			.get("/dashboard/iframe.html")
			.headers(headers0))
		.pause(1)
		.exec(http("request_38")
			.get("/dashboard/iframe.html")
			.headers(headers0)
			.resources(http("request_39")
			.get("/incidents")
			.headers(headers0),
            http("request_40")
			.post("/dashboard/756/iea5wpl1/xhr_streaming?t=1524930842119")
			.headers(headers9),
            http("request_41")
			.get("/dashboard/756/cw2a220v/htmlfile?c=_jp.afvrb23")
			.headers(headers0),
            http("request_42")
			.get("/dashboard/756/52tedi43/eventsource")
			.headers(headers14),
            http("request_43")
			.get("/css/custom.css")
			.headers(headers1),
            http("request_44")
			.get("/script/incidents_update.js")
			.headers(headers3),
            http("request_45")
			.get("/css/incidetails.css")
			.headers(headers1),
            http("request_46")
			.get("/dashboard/info?t=1524930845710")
			.headers(headers3)))
		.pause(1)
		.exec(http("request_47")
			.get("/dashboard/iframe.html")
			.headers(headers0))
		.pause(1)
		.exec(http("request_48")
			.get("/dashboard/iframe.html")
			.headers(headers0)
			.resources(http("request_49")
			.post("/dashboard/718/da2txg2n/xhr?t=1524930850154")
			.headers(headers9),
            http("request_50")
			.post("/dashboard/718/da2txg2n/xhr?t=1524930850173")
			.headers(headers9),
            http("request_51")
			.post("/dashboard/718/da2txg2n/xhr_send?t=1524930850173")
			.headers(headers10)
			.body(RawFileBody("RecordedSimulation_0051_request.txt")),
            http("request_52")
			.post("/dashboard/718/da2txg2n/xhr_send?t=1524930850217")
			.headers(headers10)
			.body(RawFileBody("RecordedSimulation_0052_request.txt"))))
		.pause(2)
		.exec(http("request_53")
			.get("/logout")
			.headers(headers0)
			.resources(http("request_54")
			.post("/dashboard/718/ic3nv5q4/xhr_streaming?t=1524930846356")
			.headers(headers9),
            http("request_55")
			.get("/dashboard/718/of4i3cvm/eventsource")
			.headers(headers14),
            http("request_56")
			.get("/dashboard/718/mr42bc45/htmlfile?c=_jp.aq0jodk")
			.headers(headers0))
			.check(status.is(500)))

	setUp(scn.inject(rampUsers(20) over(60 seconds))).protocols(httpProtocol)
}