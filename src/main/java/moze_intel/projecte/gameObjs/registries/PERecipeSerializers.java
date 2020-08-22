package moze_intel.projecte.gameObjs.registries;

import moze_intel.projecte.gameObjs.customRecipes.RecipeShapelessHidden;
import moze_intel.projecte.gameObjs.customRecipes.RecipeShapelessKleinStar;
import moze_intel.projecte.gameObjs.customRecipes.RecipesCovalenceRepair;
import moze_intel.projecte.gameObjs.registration.impl.IRecipeSerializerDeferredRegister;
import moze_intel.projecte.gameObjs.registration.impl.IRecipeSerializerRegistryObject;
import net.minecraft.item.crafting.SpecialRecipeSerializer;

public class PERecipeSerializers {

	public static final IRecipeSerializerDeferredRegister RECIPE_SERIALIZERS = new IRecipeSerializerDeferredRegister();

	public static final IRecipeSerializerRegistryObject<RecipesCovalenceRepair> COVALENCE_REPAIR = RECIPE_SERIALIZERS.register("covalence_repair", () -> new SpecialRecipeSerializer<>(RecipesCovalenceRepair::new));
	public static final IRecipeSerializerRegistryObject<RecipeShapelessKleinStar> KLEIN = RECIPE_SERIALIZERS.register("crafting_shapeless_kleinstar", RecipeShapelessKleinStar.Serializer::new);
	public static final IRecipeSerializerRegistryObject<RecipeShapelessHidden> SHAPELESS_HIDDEN = RECIPE_SERIALIZERS.register("shapeless_recipe_hidden", RecipeShapelessHidden.Serializer::new);
}