/*
 * Author: Mathis Dehez
 * Date: 17 oct. 2018
*/

package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Transform;
import platform.game.World;
import platform.util.Output;
import platform.util.View;
import platform.util.Sprite;

public class Fireball extends Actor{
	private Vector position;
	private Vector vitesse;
	private final double SIZE = 0.4;
	
	public Fireball(double a,double b,double c, double d) {
		super("fireball");
		Vector pos = new Vector(a,b);
		Vector vit = new Vector(c,d);
		if (pos==null || vit==null) throw new NullPointerException();
		position=pos;
		this.vitesse=vit;
	}
	@Override
	public Box getBox() {
		return new Box(position,SIZE,SIZE);
	}
	//Return priority
	public int getPriority() {
		return 666;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		double delta= input.getDeltaTime();
		vitesse=vitesse.add(getWorld().getGravity().mul(delta));
		position=position.add(vitesse.mul(delta));
	}

	@Override
	public void draw(Input input, Output output) {
		super.draw(input,output);
		output.drawSprite(getSprite(), getBox(), input.getTime());
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		if (other.isSolid()) {
			Vector delta = other.getBox().getCollision(position);
			if(delta != null) {
				position = position.add(delta);
				vitesse=vitesse.mirrored(delta);
			}
		}
	}
}













