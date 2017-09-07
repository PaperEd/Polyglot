package compapered.httpsgithub.firebase;

/**
 * Created by LeeJongHyun on 2017-09-07.
 */

public class Contributor {
    String login;
    String html_url;

    int contributions;

    @Override
    public String toString() {
        return login + " (" + contributions + ")";
    }
}
