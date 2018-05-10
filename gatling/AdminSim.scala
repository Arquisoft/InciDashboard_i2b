
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class AdminSim extends Simulation {

	val httpProtocol = http
		.baseURL("http://192.168.99.100:8082")
		.inferHtmlResources()
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")

	val headers_0 = Map(
		"Accept" -> "*/*",
		"Connection" -> "Keep-Alive",
		"Pragma" -> "no-cache",
		"User-Agent" -> "git/2.0 (libgit2 0.27.0)")

	val headers_1 = Map(
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "es",
		"Connection" -> "keep-alive",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) GitKraken/3.6.0 Chrome/59.0.3071.115 Electron/1.8.4 Safari/537.36",
		"accept" -> "application/vnd.github.black-cat-preview+json")

	val headers_2 = Map(
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "es",
		"Connection" -> "keep-alive",
		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) GitKraken/3.6.0 Chrome/59.0.3071.115 Electron/1.8.4 Safari/537.36",
		"accept" -> "application/vnd.github.drax-preview+json")

	val headers_3 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "es-ES,es;q=0.9",
		"Origin" -> "http://192.168.99.100:8082",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_4 = Map(
		"Accept" -> "*/*",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "es-ES,es;q=0.9")

	val headers_5 = Map(
		"Accept" -> "text/css,*/*;q=0.1",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "es-ES,es;q=0.9")

	val headers_7 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "es-ES,es;q=0.9",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_9 = Map(
		"Accept" -> "*/*",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "es-ES,es;q=0.9",
		"Origin" -> "http://192.168.99.100:8082")

	val headers_10 = Map(
		"Accept" -> "*/*",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "es-ES,es;q=0.9",
		"Content-type" -> "text/plain",
		"Origin" -> "http://192.168.99.100:8082")

	val headers_15 = Map(
		"Accept" -> "text/event-stream",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "es-ES,es;q=0.9")

    val uri1 = "https://api.github.com:443/repos/Arquisoft/InciDashboard_i2b"
    val uri3 = "https://github.com:443/Arquisoft/InciDashboard_i2b.git/info/refs"

	val scn = scenario("ExampleSim")
		.exec(http("request_0")
			.get(uri3 + "?service=git-upload-pack")
			.headers(headers_0)
			.resources(http("request_1")
			.get(uri1 + "/pulls?access_token=34d207727c28cdcdb78efca384006b97c123a8f4&per_page=100")
			.headers(headers_1),
            http("request_2")
			.get(uri1 + "?access_token=34d207727c28cdcdb78efca384006b97c123a8f4")
			.headers(headers_2)))
		.pause(70)
		.exec(http("request_3")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "operator1@dashboard.com")
			.formParam("password", "123456")
			.resources(http("request_4")
			.get("/script/incident_view/incidents_update.js")
			.headers(headers_4),
            http("request_5")
			.get("/css/incidetails.css")
			.headers(headers_5),
            http("request_6")
			.get("/dashboard/info?t=1525949851050")
			.headers(headers_4)))
		.pause(2)
		.exec(http("request_7")
			.get("/dashboard/iframe.html")
			.headers(headers_7))
		.pause(1)
		.exec(http("request_8")
			.get("/dashboard/iframe.html")
			.headers(headers_7))
		.pause(1)
		.exec(http("request_9")
			.post("/dashboard/807/s3g5l03m/xhr?t=1525949855776")
			.headers(headers_9)
			.resources(http("request_10")
			.post("/dashboard/807/s3g5l03m/xhr_send?t=1525949855796")
			.headers(headers_10)
			.body(RawFileBody("ExampleSim_0010_request.txt")),
            http("request_11")
			.post("/dashboard/807/s3g5l03m/xhr?t=1525949855797")
			.headers(headers_9),
            http("request_12")
			.post("/dashboard/807/s3g5l03m/xhr_send?t=1525949855861")
			.headers(headers_10)
			.body(RawFileBody("ExampleSim_0012_request.txt"))))
		.pause(2)
		.exec(http("request_13")
			.get("/login?logout")
			.headers(headers_7)
			.resources(http("request_14")
			.post("/dashboard/807/i5iepdh3/xhr_streaming?t=1525949851735")
			.headers(headers_9),
            http("request_15")
			.get("/dashboard/807/zu4q4gyi/eventsource")
			.headers(headers_15),
            http("request_16")
			.get("/dashboard/807/cll0vlwl/htmlfile?c=_jp.aj0nzz5")
			.headers(headers_7),
            http("request_17")
			.get("/css/custom.css")
			.headers(headers_5)))
		.pause(8)
		.exec(http("request_18")
			.get("/admin/login")
			.headers(headers_7)
			.resources(http("request_19")
			.get("/admin/css/custom.css")
			.headers(headers_5)
			.check(status.is(404)),
            http("request_20")
			.get("/admin/login")
			.headers(headers_7),
            http("request_21")
			.get("/admin/css/custom.css")
			.headers(headers_5)
			.check(status.is(404))))
		.pause(4)
		.exec(http("request_22")
			.post("/admin/login")
			.headers(headers_3)
			.formParam("email", "admin1@dashboard.com")
			.formParam("password", "123456")
			.resources(http("request_23")
			.get("/css/custom.css")
			.headers(headers_5)))
		.pause(7)
		.exec(http("request_24")
			.get("/logout")
			.headers(headers_7)
			.resources(http("request_25")
			.get("/css/custom.css")
			.headers(headers_5)))

	setUp(scn.inject(rampUsers(200) over(60 seconds))).protocols(httpProtocol)
}