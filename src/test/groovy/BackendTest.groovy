import spock.lang.Specification

/**
 * @author yaroslav.yermilov
 */
class BackendTest extends Specification {

    def files = Mock(Files)
    def backend = new Backend(files)

    def 'search for existing text'() {
        when:
            files.cat(_) >> 'data'

        then:
            backend.searchFor('data') == 0
    }

    def 'search for non-existing text'() {
        when:
        files.cat(_) >> 'data'

        then:
        backend.searchFor('non-existing') == -1
    }

    def 'load testing'() {
        when:
            files.cat(_) >> 'data'
            def concurrentCount = 100000

        then:
            concurrentCount.times {
                new Thread({
                    backend.searchFor('data') == 0
                }).start()
            }
    }

    def 'data-driven test'() {
        when:
            files.cat(_) >> data

        then:
            backend.searchFor(request) == result

        where:
            data        | request    | result
            'some data' | 'data'     | 5
            'some data' | 'some'     | 0
            'some data' | 'somedata' | -1
    }

    def 'behaviour test'() {
        when:
            backend.searchFor('non-existing')

        then:
            1 * files.cat('F:\\data.txt') >> 'data'
    }
}
