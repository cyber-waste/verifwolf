import geb.Page
import geb.spock.GebReportingSpec

class WebTest extends GebReportingSpec {

    def 'web test'() {
        when:
            to MainPage

        and:
            $('form').with {
                searchRequest = 'tests'
                $('#submit').click()
            }

        then:
            $('#result').text() == 'Result: 107'
    }
}

class MainPage extends Page {

    static url = 'http://localhost:8080/veriwolf/Main.groovy'

    static at = { $('h4').text() == 'Search through latest news' }
}