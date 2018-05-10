
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class AdministrationFinalSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://192.168.99.100:8082")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_2 = Map(
		"Origin" -> "http://192.168.99.100:8082",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_4 = Map("Accept" -> "*/*")

	val headers_8 = Map(
		"Accept" -> "*/*",
		"Origin" -> "http://192.168.99.100:8082")

	val headers_9 = Map("Accept" -> "text/event-stream")



	val scn = scenario("AdministrationFinalSimulation")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(18)
		.exec(http("request_2")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "admin1@dashboard.com")
			.formParam("password", "123456")
			.resources(http("request_3")
			.get("/css/incidetails.css")
			.headers(headers_1),
            http("request_4")
			.get("/script/incident_view/incidents_update.js")
			.headers(headers_4),
            http("request_5")
			.get("/dashboard/info?t=1525952010432")
			.headers(headers_4)))
		.pause(2)
		.exec(http("request_6")
			.get("/dashboard/iframe.html")
			.headers(headers_0)
			.resources(http("request_7")
			.get("/admin/operators")
			.headers(headers_0),
            http("request_8")
			.post("/dashboard/507/jk3m5y2c/xhr_streaming?t=1525952011317")
			.headers(headers_8),
            http("request_9")
			.get("/dashboard/507/nt5fegcz/eventsource")
			.headers(headers_9),
            http("request_10")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(10)
		.exec(http("request_11")
			.get("/logout")
			.headers(headers_0)
			.resources(http("request_12")
			.get("/css/custom.css")
			.headers(headers_1)))

	setUp(scn.inject(rampUsers(1000) over(60 seconds))).protocols(httpProtocol)
}