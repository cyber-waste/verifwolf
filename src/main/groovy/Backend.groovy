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