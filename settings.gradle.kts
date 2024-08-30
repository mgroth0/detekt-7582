pluginManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal {
            url = uri(File("/Users/matthewgroth/registered").resolve("maven").resolve("controlled"))
        }
    }
}