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
        if (b.equals(this)) {
            return 0;
        }
        double distance = calcDistance(b);
        return G * mass * b.mass / (distance * distance);
    }

    public double calcForceExertedByX(Body b) {
        if (b.equals(this)) {
            return 0;
        }
        double distance = calcDistance(b);
        double totalForce = calcForceExertedBy(b);
        return totalForce / distance * (b.xxPos - xxPos);
    }

    public double calcForceExertedByY(Body b) {
        if (b.equals(this)) {
            return 0;
        }
        double distance = calcDistance(b);
        double totalForce = calcForceExertedBy(b);
        return totalForce / distance * (b.yyPos - yyPos);
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double netForceByX = 0;
        for (Body b: bodies) {
            netForceByX += calcForceExertedByX(b);
        }
        return netForceByX;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double netForceByY = 0;
        for (Body b: bodies) {
            netForceByY += calcForceExertedByY(b);
        }
        return netForceByY;
    }

    public void update(double dt, double fX, double fY) {
        xxVel += fX / mass * dt;
        yyVel += fY / mass * dt;

        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }
}
