# MobBlockHelper
This mod was only developed to assist in the aid of the creation of certain TASes in 1.12.2 and 1.11.2 as at the time of writing RNG has not been fully fixed. This is why many things are hard-coded for the sake of getting stuff to work rather than make it customizable. 

If you need to tweak certain things for your own TAS (until KilltheRNG is stable), you should learn how to create [Mixins](https://wiki.fabricmc.net/tutorial:mixin_introduction) as that is what consists the most of this mod.

In this write up All Achievements (Used to refer to versions <= 1.11.2) will be abbreviated as "AA" and All Advancements (Versions >= 1.12) will be referred to as "AADV". 

What does this mod change?
Since this mod is intended to be used for a glitchless TAS things that are **NOT** implemented are:
- Item Duplication
- Player always invulnerable

Other miscellaneous things that are not implemented are:
- Manipulate Spawning
- Manipulate AI
- Dragon phase change

## Modified RNG Ported From LoTAS

- Items will drop towards the player (Can be forced to drop away by multiplying item velocity by -1) (from LoTAS)
- Blocks will always drop when TNT explodes
- Throwable Projectile Inaccuracy
- Unbreaking enchantment will never use durability

## Modified Entity RNG
- Fully charged bows are always critical hits
- All mobs will always drop the armor they wearing and the items they are holding
- Certain mobs will always drop the maximum amount of loot 
- Wither skeletons will never drop bones or coal but will always drop a skull

The following entity loot tables were changed to always drop the max amount of relevant loot:
- Creeper
- Enderman
- Skeleton
- Wither Skeleton
- Blaze

## Modified Block RNG
- Gravel always drops flint
- Crops always drop the maximum amount
- DeadBushes always drop 2 sticks

## Changes only relevant to AA:
- The Elder Guardian never inflicts mining fatigue on the player. To see why check [here](src/main/java/com/minecrafttas/mobblockhelper/mixin/entity/mobs/MixinEntitiyElderGuardian.java). Note that in AADV you may want to get mining fatigue as soon as possible.

- Zombies will always pick up items
- Chickens will always lay eggs at exactly 5 minutes after they are loaded

## Changes only relevant to AADV:
- Zombie villagers will always convert with the minimum possible wait

This mod **MAY** be updated if work starts on All Advancements for 1.12.2 and created without using KillTheRNG. Changes are included but not limited to:
- Removing RNG from the amount of food needed to tame certain mobs 

# Installation
This mod requires [TASmod](https://github.com/MinecraftTAS/TASmod) to be installed to properly work. It also requires an instance created using [Orinthe CLI](https://ornithemc.net/download/). In order to create the instance, download the program and execute it from the command line with the argument `prism --minecraft-version VERSION --loader-type fabric --gen 2`. The `--loader-type fabric` and `--gen 2` arguments are **required** for both TASmod and this mod to work.

# Development Setup
This mod was developed using the [Discombobulator](https://github.com/MinecraftTAS/Discombobulator) preprocessor. For more information, 
check the [wiki](https://github.com/MinecraftTAS/Discombobulator/wiki).

# Credits
The files in the [patches](src/main/java/com/minecrafttas/mobblockhelper/mixin/patches) folder despite being mostly rewritten were originally implemented from [LoTAS](https://github.com/MinecraftTAS/LoTAS). Credit goes to the original authors - Pancake, CittyKat, and Scribble.