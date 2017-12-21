# ========================================
# ============== Profiles ================
# ========================================
INSERT INTO profile (name) VALUES ("browser");
INSERT INTO profile (name) VALUES ("mobile");

# ========================================
# ============== Headers =================
# ========================================
INSERT INTO header (name) VALUES ("Content-Security-Policy");
INSERT INTO header (name) VALUES ("Referrer-Policy");
INSERT INTO header (name) VALUES ("Server");
INSERT INTO header (name) VALUES ("X-Frame-Options");
INSERT INTO header (name) VALUES ("");
INSERT INTO header (name) VALUES ("");
INSERT INTO header (name) VALUES ("");

# ========================================
# =========== Headerprofiles =============
# ========================================

# =========== Browserprofiles =============
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 1);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 2);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 3);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 4);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 5);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 6);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 7);

# =========== Mobileprofiles =============
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 1);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 2);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (2, 3);

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
INSERT INTO value (value, description, header_id, category_id) VALUES ("default-src", "Use the server as fallback for other fetch directives", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("connect-src", "Restricts the URLs which can be loaded using script interfaces", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("child-src", "Defines the valid sources for web workers and nested browsing contexts loaded using elements such as <frame> and <iframe>. Don't use this because it is depricated", 1, 2);
INSERT INTO value (value, description, header_id, category_id) VALUES ("font-src", "Specifies valid sources for fonts loaded using @font-face.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("frame-src", "Specifies valid sources for nested browsing contexts loading using elements such as <frame> and <iframe>.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("img-src", "Specifies valid sources of images and favicons.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("manifest-src", "Specifies valid sources of application manifest files.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("media-src", "Specifies valid sources for loading media using the <audio> , <video> and <track> elements.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("object-src", "Specifies valid sources for the <object>, <embed>, and <applet> elements.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("script-src", "Specifies valid sources for JavaScript.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("style-src", "Specifies valid sources for stylesheets.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("worker-src", "Specifies valid sources for Worker, SharedWorker, or ServiceWorker scripts.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("base-uri", "Restricts the URLs which can be used in a document's <base> element.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("plugin-types", "Restricts the set of plugins that can be embedded into a document by limiting the types of resources which can be loaded.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("sandbox", "Enables a sandbox for the requested resource similar to the <iframe> sandbox attribute.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("disown-opener", "Ensures a resource will disown its opener when navigated to. This should not be used because it is still expirimental and may not work as intended.", 1, 2);
INSERT INTO value (value, description, header_id, category_id) VALUES ("form-action", "Restricts the URLs which can be used as the target of a form submissions from a given context.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("frame-ancestors", "Specifies valid parents that may embed a page using <frame>, <iframe>, <object>, <embed>, or <applet>.", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("navigation-to", "Restricts the URLs to which a document can navigate by any means (a, form, window.location, window.open, etc.). This should not be used because it is expirimental and may not work as intended.", 1, 2);
INSERT INTO value (value, description, header_id, category_id) VALUES ("report-uri", "Instructs the user agent to report attempts to violate the Content Security Policy. These violation reports consist of JSON documents sent via an HTTP POST request to the specified URI. This should be used together with the report-to directive", 1, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("report-to", "Fires a SecurityPolicyViolationEvent. This should be used with the 'report-uri' directive because not all browsers support this directive. if this directive is supported it will ignore report-uri", 1, 3);



# =========== Referrer-Policy =============
INSERT INTO value (value, description, header_id, category_id) VALUES ("no-referrer", "The Referer header will be omitted entirely. No referrer information is sent along with requests.", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("no-referrer-when-downgrade", "This is the user agent's default behavior if no policy is specified. The origin is sent as referrer to a-priori as-much-secure destination (HTTPS->HTTPS), but isn't sent to a less secure destination (HTTPS->HTTP).", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("origin", "Only send the origin of the document as the referrer in all cases. The document https://example.com/page.html will send the referrer https://example.com/.", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("origin-when-cross-origin", "Send a full URL when performing a same-origin request, but only send the origin of the document for other cases, it's recommended to set this to 'strict-origin-when-cross-origin'", 2, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("same-origin", "A referrer will be sent for same-site origins, but cross-origin requests will contain no referrer information.", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("strict-origin", "Only set referrer link for HTTPS connections", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("strict-origin-when-cross-origin", "Send a full URL when performing a same-origin request, only send the origin of the document to a-priori as-much-secure destination (HTTPS->HTTPS), and send no header to a less secure destination (HTTPS->HTTP).", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("unsafe-url", "Always set the referrer url, it's recommended to set this to 'default-src' or 'strict-origin'", 2, 3);

# =========== Server =============
INSERT INTO value (value, description, header_id, category_id) VALUES ("*", "It's a good meassure to hide this information to not make it too easy for attackers", 3, 2);

# =========== X-Frame-Options =============
INSERT INTO value (value, description, header_id, category_id) VALUES ("DENY", "Completely deny loading in frames", 4, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("SAMEORIGIN", "Only load frames from the same page origin", 4, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("ALLOW-FROM", "Only load frames from whitelisted sources", 4, 1);