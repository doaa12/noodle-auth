package org.librebiz.noodleauth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Configuration {
    private static final File PREFS_DIR
            = new File(System.getProperty("user.home"), ".woath");
    private static final File PREFS_FILE
            = new File(PREFS_DIR, "prefs.properties");

    private final Properties props;

    public static Configuration load() throws IOException {
        Properties props = new Properties();
        if (PREFS_DIR.isDirectory() && PREFS_FILE.isFile()) {
            InputStream in = new FileInputStream(PREFS_FILE);
            try {
                props.load(in);
            } finally {
                in.close();
            }
        }
        return new Configuration(props);
    }

    private Configuration(Properties props) {
        this.props = props;
    }

    public void save() throws IOException {
        if (!PREFS_DIR.isDirectory()) {
            if (!PREFS_DIR.mkdirs()) {
                throw new IOException(
                        "Could not create directory " + PREFS_DIR);
            }
        }
        OutputStream out = new FileOutputStream(PREFS_FILE);
        try {
            props.store(out, "WOATH preferences");
        } finally {
            out.close();
        }
    }

    public String getString(String name, String def) {
        return props.getProperty(name, def);
    }

    public void setString(String name, String value) {
        if (value == null) {
            props.remove(name);
        } else {
            props.setProperty(name, value);
        }
    }

    public int getInt(String name, int def) {
        String s = getString(name, null);
        if (s == null) {
            return def;
        } else {
            return Integer.parseInt(s);
        }
    }

    public void setInt(String name, int value) {
        setString(name, Integer.toString(value));
    }

    public List<Account> getAccounts() {
        int count = getInt("account.count", 0);
        List<Account> result = new ArrayList<Account>();
        for (int i = 0; i < count; ++i) {
            String name = getString("account." + i + ".name", "");
            String secret = getString("account." + i + ".code", "");
            int otpLength = getInt("account." + i + ".otplength", 6);
            int duration = getInt("account." + i + ".duration", 30);
            byte[] key = Codec.BASE32.decode(secret);
            result.add(new Account(name, key, otpLength, duration));
        }
        return result;
    }

    public void setAccounts(List<Account> accounts) {
        int i = 0;
        if (accounts != null) {
            for (Account acc: accounts) {
                String secret = Codec.BASE32.encode(acc.getKey());
                setString("account." + i + ".name", acc.getName());
                setString("account." + i + ".code", secret);
                setInt("account." + i + ".otplength", acc.getOtpLength());
                setInt("account." + i + ".duration", acc.getValidity());
                ++i;
            }
        }
        setInt("account.count", i);
    }
}
