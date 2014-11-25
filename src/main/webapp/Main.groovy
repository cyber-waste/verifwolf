import sun.print.BackgroundLookupListener

/**
 * @author yaroslav.yermilov
 */
Backend backend = new Backend(new FileSystem())
String searchRequest = request.getParameter('searchRequest')

String result = searchRequest ? backend.searchFor(searchRequest) : ''

html.html {
    head {
        title 'Search through latest news'
    }
    body {
        h4 'Search through latest news'

        form (method: 'post', action: 'Main.groovy') {
            p 'Request'
            input (type: 'text', name: 'searchRequest')
            input (type: 'submit', value: 'Submit')
        }

        p "Result: ${result}"
    }
}

class Backend {

    Files files

    Backend(Files files) {
        this.files = files
    }

    int searchFor(String searchRequest) {
        String data = files.cat('F:\\data.txt')

        return data.indexOf(searchRequest)
    }
}

class Files {

    String cat(String path) {
        return new File(path).text
    }
}
