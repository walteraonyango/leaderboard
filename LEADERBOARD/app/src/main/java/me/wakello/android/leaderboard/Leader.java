package me.wakello.android.leaderboard;

public class Leader {
    private String name;
    private String hours_score;     //field to hold score for skill IQ or learning hours
    private String country;
    private String badgeUrl;

    public Leader(String name, String hours_score, String country, String badgeUrl){
        try {
            this.setName(name);
            this.setHours_score(hours_score);
            this.setCountry(country);
            this.setBadgeUrl(badgeUrl);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Get the string to be displayed under the leader's name
    public String getDetails(String leaderType){
        return getHours_score() + " "+ leaderType + ", " + getCountry() + ".";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours_score() {
        return hours_score;
    }

    public void setHours_score(String hours_score) {
        this.hours_score = hours_score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}
