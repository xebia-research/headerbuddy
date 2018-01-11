# ========================================
# ============== Default user ============
# ========================================
INSERT INTO user (apikey, email, creationdate) VALUES ("abc", "headerbuddy@default.nl", NOW());

# ========================================
# ============== Profiles ================
# ========================================
INSERT INTO profile (name) VALUES ("http");
INSERT INTO profile (name) VALUES ("https");
INSERT INTO profile (name) VALUES ("mobile");

# ========================================
# ============== Headers =================
# ========================================

# =========== Number: 1-10 ===============
INSERT INTO header (name) VALUES ("Content-Security-Policy");
INSERT INTO header (name) VALUES ("Referrer-Policy");
INSERT INTO header (name) VALUES ("Server");
INSERT INTO header (name) VALUES ("X-Frame-Options");
INSERT INTO header (name) VALUES ("Strict-Transport-Security");
INSERT INTO header (name) VALUES ("X-Powered-By");
INSERT INTO header (name) VALUES ("Last-Modified");
INSERT INTO header (name) VALUES ("X-XSS-Protection");
INSERT INTO header (name) VALUES ("X-Content-Type-Options");
INSERT INTO header (name) VALUES ("Cache-Control");

# =========== Number: 11-20 ==============
INSERT INTO header (name) VALUES ("Set-Cookie");
INSERT INTO header (name) VALUES ("Pragma");
INSERT INTO header (name) VALUES ("Public-Key-Pins");
INSERT INTO header (name) VALUES ("Server-Timing");
INSERT INTO header (name) VALUES ("X-Runtime");
INSERT INTO header (name) VALUES ("X-Content-Security-Policy");
INSERT INTO header (name) VALUES ("X-Webkit-CSP");

# ========================================
# =========== Headerprofiles =============
# ========================================

# =========== HTTP profile ============
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 1);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 2);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 3);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 4);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 6);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 7);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 8);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 9);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 10);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 11);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 12);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 13);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 14);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 15);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 16);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 17);

# =========== HTTPS profile ============
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 1);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 2);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 3);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 4);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 5);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 6);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 7);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 8);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 9);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 10);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 11);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 12);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 13);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 14);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 15);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 16);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 17);

# =========== Mobile profile =============
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (3, 1);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (3, 2);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (3, 3);

# ========================================
# ============= Categories ===============
# ========================================
INSERT INTO category(name) VALUES ("do");
INSERT INTO category(name) VALUES ("dont");
INSERT INTO category(name) VALUES ("recommendation");

# ========================================
# ============ Header values =============
# ========================================

# =========== Content-Security-Policy =============
INSERT INTO value (value, description, header_id, category_id) VALUES ("default-src", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("connect-src", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("child-src", "Defines the valid sources for web workers and nested browsing contexts loaded using elements such as <frame> and <iframe>. Don't use this because it is depricated.", 1, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("font-src", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("frame-src", "Specifies valid sources for nested browsing contexts loading using elements such as <frame> and <iframe>.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("img-src", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("manifest-src", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("media-src", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("object-src", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("script-src", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("style-src", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("worker-src", "Specifies valid sources for Worker, SharedWorker, or ServiceWorker scripts.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("base-uri", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("plugin-types", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("sandbox", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("disown-opener", "Ensures a resource will disown its opener when navigated to. This should not be used because it is still expirimental and may not work as intended.", 1, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("form-action", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("frame-ancestors", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("navigation-to", "Restricts the URLs to which a document can navigate by any means (a, form, window.location, window.open, etc.). This should not be used because it is expirimental and may not work as intended.", 1, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("report-uri", "Instructs the user agent to report violated attempts to the Content Security Policy. These violation reports consist of JSON documents sent via an HTTP POST request to the specified URI. This should be used together with the report-to directive.", 1, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("report-to", "Fires a SecurityPolicyViolationEvent. This should be used with the 'report-uri' directive because not all browsers support this directive. if this directive is supported it will ignore report-uri.", 1, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("block-all-mixed-content", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("referrer", "Used to specify information in the referer (sic) header for links away from a page. Use the Referrer-Policy header instead. This directive is depricated.", 1, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("require-sri-for", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("upgrade-insecure-requests", "This header is able to detect and mitigate Cross Site Scripting(XSS) attacks.", 1, 1);

# =========== Referrer-Policy =============
INSERT INTO value (value, description, header_id, category_id) VALUES ("no-referrer", "The Referer header will be omitted entirely. No referrer information is sent along with requests.", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("no-referrer-when-downgrade", "This is the user agent's default behavior if no policy is specified. The origin is sent as referrer to a-priori as-much-secure destination (HTTPS->HTTPS), but isn't sent to a less secure destination (HTTPS->HTTP).", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("origin", "Only send the origin of the document as the referrer in all cases. The document https://example.com/page.html will send the referrer https://example.com/.", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("origin-when-cross-origin", "Send a full URL when performing a same-origin request, but only send the origin of the document for other cases, it's recommended to set this to 'strict-origin-when-cross-origin'.", 2, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("same-origin", "A referrer will be sent for same-site origins, but cross-origin requests will contain no referrer information", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("strict-origin", "Only set referrer link for HTTPS connections", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("strict-origin-when-cross-origin", "Send a full URL when performing a same-origin request, only send the origin of the document to a-priori as-much-secure destination (HTTPS->HTTPS), and send no header to a less secure destination (HTTPS->HTTP).", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("unsafe-url", "This will always set the referrer url whether it's a trusted source or not. It's recommended to set this to 'default-src' or 'strict-origin'.", 2, 3);

# =========== Server =============
INSERT INTO value (value, description, header_id, category_id) VALUES ("*", "Shows potential vulnerable information of what software is being used on the server. It's also possible to set this header with misleading information.", 3, 2);

# =========== X-Frame-Options =============
INSERT INTO value (value, description, header_id, category_id) VALUES ("DENY", "This header is set to define from which sources the site is allowed to load sources from, and consequently avoids clickjacking attacks.", 4, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("SAMEORIGIN", "This header is set to define from which sources the site is allowed to load sources from, and consequently avoids clickjacking attacks.", 4, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("ALLOW-FROM", "This header is set to define from which sources the site is allowed to load sources from, and consequently avoids clickjacking attacks.", 4, 1);

# =========== Strict-Transport-Security =============
INSERT INTO value (value, description, header_id, category_id) VALUES ("max-age", "This header is set to only allow HTTPS connections (or redirects to HTTPS) and thus prevents the man-in-the-middle attacks", 5, 1);

# =========== X-Powered-By ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("*", "This header shows potential vulnerable server information. It's recommended to remove this header.", 6, 2);

# =========== Last-Modified ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("*", "This header shows potential vulnerable server information. It's recommended to remove this header.", 7, 2);

# =========== X-XSS-Protection ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("0", "Disables XSS filtering, it's recommended to set this to 1 because older browser may not support the new headers.", 8, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("1", "Enables XSS filtering (usually set by default in newer browsers). If a cross-site scripting attack is detected, the browser will sanitize the page (remove the unsafe parts) or even stop the whole page from rendering.", 8, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("1; mode=block", "Enables XSS filtering (usually set by default in newer browsers). If a cross-site scripting attack is detected, the browser will sanitize the page (remove the unsafe parts) or even stop the whole page from rendering.", 8, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("1; report", "Enables XSS filtering (usually set by default in newer browsers). If a cross-site scripting attack is detected, the browser will sanitize the page (remove the unsafe parts) or even stop the whole page from rendering.", 8, 1);

# =========== X-Content-Type-Options ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("nosniff", "Set this header against MIME type sniffing.", 9, 1);

# =========== Cache-Control ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("must-revalidate", "The cache must verify the status of the stale resources before using it and expired ones should not be used. Because this header is depened on how the website is being used it's adviced to double check if you really need this.", 10, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("no-cache", "Forces caches to submit the request to the origin server for validation before releasing a cached copy. Because this header is depened on how the website is being used it's adviced to double check if you really need this.", 10, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("no-store", "The cache should not store anything about the client request or server response. Because this header is depened on how the website is being used it's adviced to double check if you really need this.", 10, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("no-transform", "No transformations or conversions should be made to the resource. The Content-Encoding, Content-Range, Content-Type headers must not be modified by a proxy. A non- transparent proxy might, for example, convert between image formats in order to save cache space or to reduce the amount of traffic on a slow link. The no-transform directive disallows this. Because this header is depened on how the website is being used it's adviced to double check if you really need this.", 10, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("public", "Indicates that the response may be cached by any cache. Because this header is depened on how the website is being used it's adviced to double check if you really need this. Because this header is depened on how the website is being used it's adviced to double check if you really need this.", 10, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("private", "Indicates that the response is intended for a single user and must not be stored by a shared cache. A private cache may store the response. Because this header is depened on how the website is being used it's adviced to double check if you really need this.", 10, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("proxy-revalidate", "Same as must-revalidate, but it only applies to shared caches (e.g., proxies) and is ignored by a private cache. Because this header is depened on how the website is being used it's adviced to double check if you really need this.", 10, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("max-age", "Specifies the maximum amount of time a resource will be considered fresh. Contrary to Expires, this directive is relative to the time of the request. Because this header is depened on how the website is being used it's adviced to double check if you really need this.", 10, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("s-maxage", "Overrides max-age or the Expires header, but it only applies to shared caches (e.g., proxies) and is ignored by a private cache. Because this header is depened on how the website is being used it's adviced to double check if you really need this.", 10, 3);

# =========== Set-Cookie ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("Expires", "It's not recommended to configure the header like this. It enables the client to change the value of the time stamp which can keep the session alive a lot longer.", 11, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("Max-Age", "Number of seconds until the cookie expires. A zero or negative number will expire the cookie immediately. Older browsers (ie6, ie7, and ie8) do not support max-age. For other browsers, if both Expires and Max-Age are set, Max-Age will have precedence.", 11, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("Domain", "Make sure that this domain can be publicly shown.", 11, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("Secure", "A secure cookie will only be sent to the server when a request is made using SSL and the HTTPS protocol. However, confidential or sensitive information should never be stored or transmitted in HTTP Cookies as the entire mechanism is insecure.", 11, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("HttpOnly", "HTTP-only cookies aren't accessible via JavaScript through the Document.cookie property, the XMLHttpRequest and Request APIs to mitigate attacks against cross-site scripting (XSS).", 11, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("SameSite=Strict", "Allows servers to assert that a cookie ought not to be sent along with cross-site requests, which provides some protection against cross-site request forgery attacks (CSRF).", 11, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("SameSite=Lax", "Allows servers to assert that a cookie ought not to be sent along with cross-site requests, which provides some protection against cross-site request forgery attacks (CSRF).", 11, 1);

# =========== Pragma ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("no-cache", "Same as Cache-Control: no-cache. Forces caches to submit the request to the origin server for validation before releasing a cached copy, It's recommended to use this only for backwards compatibility, it's recommended to use cache-control: no-cache instead.", 12, 3);

# =========== Public-Key-Pins ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("pin-sha256", "The quoted string is the Base64 encoded Subject Public Key Information (SPKI) fingerprint. It is possible to specify multiple pins for different public keys. Some browsers might allow other hashing algorithms than SHA-256 in the future, HPKP has the potential to lock out users for a long time if used incorrectly! The use of backup certificates and/or pinning the CA certificate is recommended.", 13, 3);

# =========== Server-Timing ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("*", "Never use this header in a live situations, this makes the server an easier target for potential attackers.", 14, 2);

# =========== X-Runtime ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("*", "This header can show potential vulnerable information. It's recommended to remove this header.", 15, 2);

# =========== X-Content-Security-Policy ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("*", "Do not use this header because it's outdated. It's recommended to use 'Content-Security-Policy' instead. Note: Implementing both 'Content-Security-Policy' and 'X-Content-Security-Policy' can cause problems depending on the used browsers.", 16, 3);

# =========== X-Webkit-CSP ===========
INSERT INTO value (value, description, header_id, category_id) VALUES ("*", "Do not use this header because it's outdated. It's recommended to use 'Content-Security-Policy' instead. Note: Implementing both 'Content-Security-Policy' and 'X-Webkit-CSP' can cause problems depending on the used browsers.", 17, 3);

