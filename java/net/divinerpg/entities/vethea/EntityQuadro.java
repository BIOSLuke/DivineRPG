package net.divinerpg.entities.vethea;

import net.divinerpg.api.entity.EntityDivineRPGBoss;
import net.divinerpg.api.entity.EntityStats;
import net.divinerpg.entities.vanilla.projectile.EntityDivineArrow;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityQuadro extends EntityDivineRPGBoss {

	public int ability;
	private final int SLOW = 0;
	private final int FAST = 1;
	private final int MSLOW = 2;
	private final int MFAST = 3;

	private int abilityCoolDown;

	private int rangedAttackCounter;
	public boolean dir;

	public EntityQuadro(World w) {
		super(w);
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1, false));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 80));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		ability = SLOW;
		this.setSize(2F, 2F);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(22);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(net.divinerpg.api.entity.EntityStats.quadroHealth);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(net.divinerpg.api.entity.EntityStats.quadroSpeedFast);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(net.divinerpg.api.entity.EntityStats.quadroFollowRange);
	}

	@Override
	protected void updateAITasks() {
		if(!this.worldObj.isRemote && this.entityToAttack != null && this.entityToAttack instanceof EntityLivingBase) this.attackEntityWithRangedAttack((EntityLivingBase)this.entityToAttack, 0);
		if (this.abilityCoolDown == 0) {
			ability = this.rand.nextInt(4);
			this.abilityCoolDown = 500;
			this.rangedAttackCounter = 0;
			this.dir = true;
			switch(this.rand.nextInt(9)) {
			case 0:
				this.playSound(Sounds.quadroDieBefore.getPrefixedName(), 1.0F, 1.0F);
				if(!this.worldObj.isRemote)
					Util.sendMessageToAll("Quadro: Die before me!");
				break;
			case 1:
				this.playSound(Sounds.quadroEnough.getPrefixedName(), 1.0F, 1.0F);
				if(!this.worldObj.isRemote)
					Util.sendMessageToAll("Quadro: Enough of you! You don't deserve my kill!");
				break;
			case 2:
				this.playSound(Sounds.quadroPunch.getPrefixedName(), 1.0F, 1.0F);
				if(!this.worldObj.isRemote)
					Util.sendMessageToAll("Quadro: Incoming punch!");
				break;
			case 3:
				this.playSound(Sounds.quadroIsNext.getPrefixedName(), 1.0F, 1.0F);
				if(!this.worldObj.isRemote)
					Util.sendMessageToAll("Quadro: Looks like im done with this. You're next!");
				break;
			case 4:
				this.playSound(Sounds.quadroKillMine.getPrefixedName(), 1.0F, 1.0F);
				if(!this.worldObj.isRemote)
					Util.sendMessageToAll("Quadro: This kill is mine!");
				break;
			case 5:
				this.playSound(Sounds.quadroMyKill.getPrefixedName(), 1.0F, 1.0F);
				if(!this.worldObj.isRemote)
					Util.sendMessageToAll("Quadro: You're my kill!");
				break;
			case 6:
				this.playSound(Sounds.quadroNoDie.getPrefixedName(), 1.0F, 1.0F);
				if(!this.worldObj.isRemote)
					Util.sendMessageToAll("Quadro: No! Die before me!");
				break;
			case 7:
				this.playSound(Sounds.quadroSitDown.getPrefixedName(), 1.0F, 1.0F);
				if(!this.worldObj.isRemote){
					Util.sendMessageToAll("Quadro: Sit down human!");
					Util.sendMessageToAll("Quadro: You don't deserve to be near a guardian of Arksiane!");
				}
				break;
			default:
				this.playSound(Sounds.quadroTasteFist.getPrefixedName(), 1.0F, 1.0F);
				if(!this.worldObj.isRemote)
					Util.sendMessageToAll("Quadro: Taste fist!");
				break;
			}
		}
		if (this.abilityCoolDown == 480) {
			this.abilityCoolDown--;
			this.dir = false;
		}
		if (this.abilityCoolDown > 0) {
			this.abilityCoolDown--;
		}

		if (ability == MSLOW) {
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.quadroSpeedSlow);
			this.setAIMoveSpeed((float)EntityStats.quadroSpeedSlow);
		}
		else if (ability == MFAST) {
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.quadroSpeedFast);
			this.setAIMoveSpeed((float)EntityStats.quadroSpeedFast);
		}
		else if (ability == SLOW) {
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0);
			this.setAIMoveSpeed(0);
		}
		else if (ability == FAST) {
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0);
			this.setAIMoveSpeed(0);
		}
		super.updateAITasks();
	}

	@Override
	protected float getSoundVolume() {
		return 0.7F;
	}

	@Override
	protected String getLivingSound() {
		return null;
	}

	@Override
	protected String getHurtSound() {
		return getLivingSound();
	}

	@Override
	protected String getDeathSound() {
		return getHurtSound();
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		this.dropItem(VetheaItems.quadroticLump, 25);
	}

	public void attackEntityWithRangedAttack(EntityLivingBase par1, float par2) {
		switch(ability) {
            case FAST:
            	if ((this.rangedAttackCounter % 5) == 0) {
            		EntityDivineArrow var2 = new EntityDivineArrow(this.worldObj, this, par1, 1.6f, 6f, 1, "quadroArrow");
                	this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
                	this.worldObj.spawnEntityInWorld(var2);
            	}
            	this.rangedAttackCounter++;
                break;
            case SLOW:
                if ((this.rangedAttackCounter % 15) == 0) {
                	EntityDivineArrow var4 = new EntityDivineArrow(this.worldObj, this, par1, 1.6f, 6f, 2, "quadroArrow");
                    this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
                    this.worldObj.spawnEntityInWorld(var4);
                }
                this.rangedAttackCounter++;
                break;
            default: 
            	break;
        }
	}

	@Override
	public String mobName() {
		return "Quadro";
	}

	@Override
	public String name() {
		return "Quadro";
	}

	@Override
	public IChatComponent chat() {
		return null;
	}
}