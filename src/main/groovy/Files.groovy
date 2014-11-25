class Files {

    String cat(String path) {
        return new File(path).text
    }
}