package poseidon.mod.objects.block.particlerods;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;
import poseidon.mod.Main;
import poseidon.mod.util.ParticleUtil;

public class TileEntityParticleRod extends TileEntity implements ITickable
{
	double step = 0;
	double forl = 40;	//hoogte om te beginnen/eindgen
	static double s;
	int maxStep = 20; //lengte van het particle effect.
	public int choice = 0;
	
	void setStep(double step)
	{
		s = step;
	}
	
	void reset() {
		step = 0;
		double forl = 40;
		maxStep = 20;
	}
	
	@Override
	public void update()
	{		
		if(!this.world.isRemote && step <= maxStep)
		{
			//Object uit Particle.java maken.
			//meerdere Objecten = meer particle stroken.
			
			
			/* spiraal horizontaal, step = 0, maxStep = 500 */
			//Particle horizontalSpiral = new Particle(/*step*/0.01, 0.01, 0.05, /*xvals*/1, 10, 0, 0, 0, /*yvals*/1, 10, 0, 0, 0, /*zvals*/0, 0, 0, 0.05, 0, /*cos.sin.tan.rechte.exp.log.1/x.0*/1, 0, 4);
			//Main.proxy.customParticle(this.world, horizontalSpiral.getResultX() + this.pos.getX() + 0.5d, horizontalSpiral.getResultY() + this.pos.getY() + 0.5d, horizontalSpiral.getResultZ() + this.pos.getZ() + 0.5d);
			
			
			/* kegel maken, step = 0, forl = 20, maxStep = 20.

			
			
			step++;
			setStep(step);
			*/
			
			/* wing, step = 0, forl = 20, maxStep = 20.

			

			
			step++;
			setStep(step);
			*/
			
			/* wing 2, step = 0, forl = 20, maxStep = 20.

			
			
			step++;
			setStep(step);
			*/
			
			switch(choice) {
			case 1: 
				reset();
				wing1();
				break;
			case 2: 
				reset();
				wing2();
				break;
			case 3: 
				reset();
				wing3();
				break;
			case 4: 
				reset();
				wing4();
				break;
			case 5: 
				reset();
				wing5();
				break;
			case 6: 
				reset();
				wing6();
				break;
			case 7: 
				reset();
				wing7();
				break;
			case 8: 
				reset();
				wing8();
				break;
			}
			
			step++;
			setStep(step);
			
			return;
		}
		
		if(step >= maxStep) step = 0;
		
	}
	
	private void wing1() {
		ParticleCX wing2X = new ParticleCX();
		ParticleCY wing2Y = new ParticleCY();
		ParticleCZ wing2Z = new ParticleCZ();
		
		wing2Y = new ParticleCY(0 + forl/10, 0, 0, 0, 0, 0, 7);
		for(float i = 190f; i > 0f; i -= 0.25f)
		{
			setStep(i);
			wing2X = new ParticleCX(0.02, (1 - forl/10)*(i), 0.07, 0, 0, 0, 0);
			wing2Z = new ParticleCZ(0.02, (1 - forl/10)*(i), 0.07, 0, 0, 0, 1);
			//debug
			//if(i%10 == 0) 
			//	System.out.println("x: " + wing2X.getResultX() + "z: " + wing2Z.getResultZ());
			
			if(!world.isRemote) ParticleUtil.customParticle((WorldServer) this.world, wing2X.getResultX()/5 + this.pos.getX() + 0.5d, wing2Y.getResultY() + this.pos.getY() + 0.5d, wing2Z.getResultZ()/3 + this.pos.getZ() + 0.5d);
			if(i == 1.0f)
				forl--;
		
		}
	}
	
	private void wing2() {
		ParticleCX wing2X1 = new ParticleCX();
		ParticleCY wing2Y1 = new ParticleCY();
		ParticleCZ wing2Z1 = new ParticleCZ();
		
		wing2Y1 = new ParticleCY(0 + forl/10, 0, 0, 0, 0, 0, 7);
		for(float i = 190f; i > 0f; i -= 0.25f)
		{
			setStep(i);
			wing2X1 = new ParticleCX(0.02, (1 - forl/10)*(i), 0.07, 3.14, 0, 0, 1);
			wing2Z1 = new ParticleCZ(0.02, (1 - forl/10)*(i), 0.07, 3.14, 0, 0, 0);
			
			//debug
			//if(i%10 == 0) 
			//	System.out.println("x: " + wing2X1.getResultX() + ", z: " + wing2Z1.getResultZ());
	
			if(!world.isRemote) ParticleUtil.customParticle((WorldServer) this.world, wing2X1.getResultX()/3 + this.pos.getX() + 0.5d, wing2Y1.getResultY() + this.pos.getY() + 0.5d, wing2Z1.getResultZ()/5 + this.pos.getZ() + 0.5d);
			if(i == 1.0f)
				forl--;
		
		}

	}
	
	private void wing3() {
		ParticleCX wing2X1 = new ParticleCX();
		ParticleCY wing2Y1 = new ParticleCY();
		ParticleCZ wing2Z1 = new ParticleCZ();
		
		wing2Y1 = new ParticleCY(0 + forl/10, 0, 0, 0, 0, 0, 7);
		for(int i = 190; i > 0; i--)
		{
			setStep(i);
			wing2X1 = new ParticleCX(0.02, (1 - forl/10)*(i/3), 0.07, 3.14, 0, 0, 1);
			wing2Z1 = new ParticleCZ(0.02, (1 - forl/10)*(i/3), 0.07, 3.14, 0, 0, 0);
			//debug
			//if(i%10 == 0) 
			//	System.out.println("x: " + reverseConeX.getResultX() + "z: " + reverseConeZ.getResultZ());
			if(!world.isRemote) ParticleUtil.customParticle((WorldServer) this.world, wing2X1.getResultX()/2 + this.pos.getX() + 0.5d, wing2Y1.getResultY() + this.pos.getY() + 0.5d, wing2Z1.getResultZ()/4 + this.pos.getZ() + 0.5d);
			if(i == 1)
				forl--;
		
		}
	}
	
	private void wing4() {
		ParticleCX wing2X = new ParticleCX();
		ParticleCY wing2Y = new ParticleCY();
		ParticleCZ wing2Z = new ParticleCZ();
		
		wing2Y = new ParticleCY(0 + forl/10, 0, 0, 0, 0, 0, 7);
		for(int i = 190; i > 0; i--)
		{
			setStep(i);
			wing2X = new ParticleCX(0.02, (1 - forl/10)*(i/3), 0.07, 0, 0, 0, 0);
			wing2Z = new ParticleCZ(0.02, (1 - forl/10)*(i/3), 0.07, 0, 0, 0, 1);
			//debug
			//if(i%10 == 0) 
			//	System.out.println("x: " + reverseConeX.getResultX() + "z: " + reverseConeZ.getResultZ());
			if(!world.isRemote) ParticleUtil.customParticle((WorldServer) this.world, wing2X.getResultX()/4 + this.pos.getX() + 0.5d, wing2Y.getResultY() + this.pos.getY() + 0.5d, wing2Z.getResultZ()/2 + this.pos.getZ() + 0.5d);
			if(i == 1)
				forl--;
		
		}
		
	}

	private void wing5() {
		ParticleCX wingX1 = new ParticleCX();
		ParticleCY wingY1 = new ParticleCY();
		ParticleCZ wingZ1 = new ParticleCZ();
		
		wingY1 = new ParticleCY(0 + forl/10, 0, 0, 0, 0, 0, 7);
		for(int i = 190; i > 0; i--)
		{
			setStep(i);
			wingX1 = new ParticleCX(0.2, (1 - forl/10)*(i/2), 0.07, 3.14, 0, 0, 1);
			wingZ1 = new ParticleCZ(0.2, (1 - forl/10)*(i/2), 0.07, 3.14, 0, 0, 0);
			//debug
			//if(i%10 == 0) 
			//	System.out.println("x: " + reverseConeX.getResultX() + "z: " + reverseConeZ.getResultZ());
			if(!world.isRemote) ParticleUtil.customParticle((WorldServer) this.world, wingX1.getResultX() + this.pos.getX() + 0.5d, wingY1.getResultY() + this.pos.getY() + 0.5d, wingZ1.getResultZ() + this.pos.getZ() + 0.5d);
			if(i == 1)
				forl--;
		
		}
	}
	
	private void wing6() {
		ParticleCX wingX = new ParticleCX();
		ParticleCY wingY = new ParticleCY();
		ParticleCZ wingZ = new ParticleCZ();
		
		wingY = new ParticleCY(0 + forl/10, 0, 0, 0, 0, 0, 7);
		for(int i = 190; i > 0; i--)
		{
			setStep(i);
			wingX = new ParticleCX(0.2, (1 - forl/10)*(i/2), 0.07, 0, 0, 0, 0);
			wingZ = new ParticleCZ(0.2, (1 - forl/10)*(i/2), 0.07, 0, 0, 0, 1);
			//debug
			//if(i%10 == 0) 
			//	System.out.println("x: " + reverseConeX.getResultX() + "z: " + reverseConeZ.getResultZ());
			if(!world.isRemote) ParticleUtil.customParticle((WorldServer) this.world, wingX.getResultX() + this.pos.getX() + 0.5d, wingY.getResultY() + this.pos.getY() + 0.5d, wingZ.getResultZ() + this.pos.getZ() + 0.5d);
			if(i == 1)
				forl--;
		
		}
	}
	
	private void wing7() {
		ParticleCX wingX = new ParticleCX();
		ParticleCY wingY = new ParticleCY();
		ParticleCZ wingZ = new ParticleCZ();
		
		wingY = new ParticleCY(0 + forl/10, 0, 0, 0, 0, 0, 7);
		for(int i = 190; i > 0; i--)
		{
			setStep(i);
			wingX = new ParticleCX(0.2, (1 - forl/10)*(i/2), 0.07, 0, 0, 0, 0);
			wingZ = new ParticleCZ(0.2, (1 - forl/10)*(i/2), 0.07, 0, 0, 0, 1);
			//debug
			//if(i%10 == 0) 
			//	System.out.println("x: " + reverseConeX.getResultX() + "z: " + reverseConeZ.getResultZ());
			if(!world.isRemote) ParticleUtil.customParticle((WorldServer) this.world, wingX.getResultX() + this.pos.getX() + 0.5d, wingY.getResultY() + this.pos.getY() + 0.5d, wingZ.getResultZ() + this.pos.getZ() + 0.5d);
			if(i == 1)
				forl--;
		
		}
	}
	
	private void wing8() {
		ParticleCX reverseConeX = new ParticleCX();
		ParticleCY reverseConeY = new ParticleCY();
		ParticleCZ reverseConeZ = new ParticleCZ();
		
		reverseConeY = new ParticleCY(0 + forl/10, 0, 0, 0, 0, 0, 7);
		for(int i = 190; i > 0; i--)
		{
			setStep(i);
			reverseConeX = new ParticleCX(0.2, 3 - forl/10, 0.17, 0, 0, 0, 0); // + i÷10 geeft spiraal.
			reverseConeZ = new ParticleCZ(0.2, 3 - forl/10, 0.17, 0, 0, 0, 1);
			//debug
			//if(i%10 == 0) 
			//	System.out.println("x: " + reverseConeX.getResultX() + "z: " + reverseConeZ.getResultZ());
			if(!world.isRemote) ParticleUtil.customParticle((WorldServer) this.world, reverseConeX.getResultX() + this.pos.getX() + 0.5d, reverseConeY.getResultY() + this.pos.getY() + 0.5d, reverseConeZ.getResultZ() + this.pos.getZ() + 0.5d);
			if(i == 1)
				forl--;
		
		}
	}
	
}
