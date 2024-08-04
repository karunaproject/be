package karuna.karuna_backend

import org.springframework.security.core.context.SecurityContextHolder

trait Constants {

    static final String PAGE = "HOME"
    static final String KEY = "home.key"
    static final String VALUE = "Home value"

    static final String TO = "test@test.pl"
    static final String SUBJECT = "Test KARUNA"
    static final String CONTENT_TEXT = "To tylko test"
    static final String CONTENT_HTML = "<h1>TEST</h1>"

    static final String BODY = "Example body"
    static final String USERNAME = "REVO"
    static final String ROLE_USER = "ROLE_USER"
    static final String TOKEN = "Bearer "

}