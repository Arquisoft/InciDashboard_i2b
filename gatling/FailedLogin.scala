
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class FailedLogin extends Simulation {

	val httpProtocol = http
		.baseURL("http://192.168.99.100:8082")
		.proxy(Proxy("192.168.99.100", 8082).httpsPort(8082))
		.inferHtmlResources()
		.acceptHeader("image/webp,image/apng,image/*,*/*;q=0.8")
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

	val headers2 = Map("Proxy-Connection" -> "keep-alive")

	val headers3 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Origin" -> "http://192.168.99.100:8082",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")



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
			.formParam("username", "admin")
			.formParam("password", "hello")
			.resources(http("request_4")
			.get("/favicon.ico")
			.headers(headers2)
			.check(status.is(500)))
			.check(status.is(500)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}