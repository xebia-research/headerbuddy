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

# ========================================
# =========== Headerprofiles =============
# ========================================
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 1);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 2);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 3);
INSERT INTO profile_headers(profiles_id, headers_id) VALUES (1, 4);
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
INSERT INTO value (value, description, header_id, category_id) VALUES ("default-src", "Use the server as fallback for other fetch directives", 1, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("strict-origin", "Only set referrer link for HTTPS connections", 2, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("unsafe-url", "Always set the referrer url", 2, 3);
INSERT INTO value (value, description, header_id, category_id) VALUES ("*", "It's a good meassure to hide this information to not make it too easy for attackers", 3, 2);
INSERT INTO value (value, description, header_id, category_id) VALUES ("DENY", "Completely deny loading in frames", 4, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("SAMEORIGIN", "Only load frames from the same page origin", 4, 1);
INSERT INTO value (value, description, header_id, category_id) VALUES ("ALLOW-FROM", "Only load frammes from whitelisted sources", 4, 1);