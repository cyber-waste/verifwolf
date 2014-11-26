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
}
