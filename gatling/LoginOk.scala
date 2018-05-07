
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class LoginOk extends Simulation {

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



	val scn = scenario("RecordedSimulation")
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
		.pause(15)
		.exec(http("request_3")
			.post("/login")
			.headers(headers3)
			.formParam("username", "operator1@dashboard.com")
			.formParam("password", "123456")
			.resources(http("request_4")
			.get("/css/custom.css")
			.headers(headers1),
            http("request_5")
			.get("/script/incidents_update.js")
			.headers(headers5),
            http("request_6")
			.get("/css/incidetails.css")
			.headers(headers1),
            http("request_7")
			.get("/dashboard/info?t=1524930485722")
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
			.post("/dashboard/245/xa4k2xxy/xhr?t=1524930490333")
			.headers(headers11),
            http("request_12")
			.post("/dashboard/245/xa4k2xxy/xhr_send?t=1524930490365")
			.headers(headers12)
			.body(RawFileBody("RecordedSimulation_0012_request.txt")),
            http("request_13")
			.post("/dashboard/245/xa4k2xxy/xhr?t=1524930490365")
			.headers(headers11),
            http("request_14")
			.post("/dashboard/245/xa4k2xxy/xhr_send?t=1524930490431")
			.headers(headers12)
			.body(RawFileBody("RecordedSimulation_0014_request.txt"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}