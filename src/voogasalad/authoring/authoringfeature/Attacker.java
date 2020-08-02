package voogasalad.authoring.authoringfeature;

public class Attacker extends AbstractFeature {
    private BasicGameObjectConfigurations basicConfigurations;
    private int damage;
    private int speed;

    public Attacker(String imagepath, String name, int health, int damage, int speed) {
        super(name, imagepath);
        this.basicConfigurations = new BasicGameObjectConfigurations(health);
        this.damage = damage;
        this.speed = speed;
    }

    public BasicGameObjectConfigurations getBasicConfigurations() {
        return basicConfigurations;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public AbstractFeature deepCopy() {
        Attacker r = new Attacker(getImagePath(), getName(), getBasicConfigurations().getHealth(), getDamage(), getSpeed());
        r.setId(getID());
        return r;
    }

    @Override
    public boolean compare(AbstractFeature o) {
        Attacker obj = (Attacker) o;
        if (!(obj.getID() == getID()) || !(obj.getImagePath().equals(getImagePath())) || !(obj.getName().equals(getName()))) {
            return false;
        }
        if (!(obj.damage == damage || !(obj.speed == speed) || !(obj.getName().equals(getName()))) || !(basicConfigurations.compare(obj.basicConfigurations))) {
            return false;
        }
        return true;
    }
}
