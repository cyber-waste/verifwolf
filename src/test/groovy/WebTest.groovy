import geb.Page
import geb.spock.GebReportingSpec
import spock.lang.Stepwise

@Stepwise
class WebTest extends GebReportingSpec {

    def 'web test with non-existing data'() {
        when:
        to MainPage

        and:
        $('form').with {
            searchRequest = 'non-existing data'
            $('#submit').click()
        }

        then:
        $('#result').text() == 'Result: -1'
    }

    def 'web test with existing data'() {
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