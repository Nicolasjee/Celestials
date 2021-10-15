package poseidon.mod.objects.block.particlerods;

class ParticleCY 
{
	double resultx, resulty, resultz;
	
	public ParticleCY()
	{
		
	}
	
	public ParticleCY(double yStep, double yA, double yw, double ya, double yh, double yl, int y)
	{
		switch(y)
		{
			case 0:
				resulty = yA*Math.cos(yw*(yStep*TileEntityParticleRod.s) + ya) + yh*TileEntityParticleRod.s + yl;
				break;
			case 1:
				resulty = yA*Math.sin(yw*(yStep*TileEntityParticleRod.s) + ya) + yh*TileEntityParticleRod.s + yl;
				break;
			case 2:
				resulty = yA*Math.tan(yw*(yStep*TileEntityParticleRod.s) + ya) + yh*TileEntityParticleRod.s + yl;
				break;
			case 3:
				resulty = yStep*TileEntityParticleRod.s + yh*TileEntityParticleRod.s + yl;
				break;
			case 4:
				resulty = Math.pow((double) yStep, TileEntityParticleRod.s) + yh*TileEntityParticleRod.s + yl;
				break;
			case 5:
				resulty = Math.log(yStep*TileEntityParticleRod.s) + yh*TileEntityParticleRod.s + yl;
				break;
			case 6:
				resulty = (1*yh)/(yStep*TileEntityParticleRod.s) + ya*TileEntityParticleRod.s + yl; 
				break;
			case 7:
				resulty = yStep; 
				break;
			case 8:
				resulty = 0;
				break;
		}
	}
	
	double getResultY() { return resulty; }
}