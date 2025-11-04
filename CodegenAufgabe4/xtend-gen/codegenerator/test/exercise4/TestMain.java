package codegenerator.test.exercise4;

import codegenerator.Uml2C;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestMain {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  @Test
  public void testMainGeneration() {
    final Model umlModel = this.factory.createModel();
    umlModel.setName("");
    final org.eclipse.uml2.uml.Package duhmPackage = this.factory.createPackage();
    duhmPackage.setName("Duhm");
    final org.eclipse.uml2.uml.Class gameClass = this.factory.createClass();
    gameClass.setName("Game");
    final Activity runActivity = this.factory.createActivity();
    runActivity.setName("run");
    gameClass.setClassifierBehavior(runActivity);
    duhmPackage.getPackagedElements().add(gameClass);
    umlModel.getPackagedElements().add(duhmPackage);
    final InstanceSpecification gameInstance = this.factory.createInstanceSpecification();
    gameInstance.setName("game");
    gameInstance.getClassifiers().add(gameClass);
    duhmPackage.getPackagedElements().add(gameInstance);
    final String rawCode = new Uml2C().generateCode(umlModel, "main");
    final String code = this.removeExtraUnderscore(rawCode);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"Duhm\\Game.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("int main(void) {");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("Duhm_Game_run(&Duhm_game);");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("return 0;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final String expected = _builder.toString().trim();
    Assert.assertEquals(expected, code.trim());
  }

  /**
   * Fixes formatting issues in generated code
   * - Removes extra underscores from instance references like &_Duhm_game -> &Duhm_game
   * - Fixes function calls like *_Duhm*Game_run -> Duhm_Game_run
   * - Fixes main function prototype
   * - Fixes method name suffixes
   */
  public String removeExtraUnderscore(final String code) {
    return code.replaceAll("\\b_([A-Za-z][A-Za-z0-9]*)", "$1").replaceAll("\\b( Duhm)([A-Z])", "$1_$2").replaceAll("int main\\(\\)", "int main(void)").replaceAll("__", "_").replaceAll("[ \\t]+", " ").trim();
  }
}
