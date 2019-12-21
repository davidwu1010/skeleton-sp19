public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
    }

    public double calcDistance(Body b) {
        return Math.sqrt(Math.pow(xxPos - b.xxPos, 2) + Math.pow(yyPos - b.yyPos, 2));
    }

    public double calcForceExertedBy(Body b) {
        double distance = calcDistance(b);
        return G * mass * b.mass / (distance * distance);
    }
}
