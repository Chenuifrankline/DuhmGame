package codegenerator.test;

import codegenerator.Uml2C;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralNull;
import org.eclipse.uml2.uml.LiteralReal;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestLiteralSpec {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  @Test
  public void testEmptyLiteralInteger() {
    final LiteralInteger literal = this.factory.createLiteralInteger();
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("0", code);
  }

  @Test
  public void testNullLiteralInteger() {
    LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
    final Procedure1<LiteralInteger> _function = (LiteralInteger it) -> {
      it.setValue(0);
    };
    final LiteralInteger literal = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function);
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("0", code);
  }

  @Test
  public void testPositiveLiteralInteger() {
    LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
    final Procedure1<LiteralInteger> _function = (LiteralInteger it) -> {
      it.setValue(2147483647);
    };
    final LiteralInteger literal = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function);
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("2147483647", code);
  }

  @Test
  public void testNegativeLiteralInteger() {
    LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
    final Procedure1<LiteralInteger> _function = (LiteralInteger it) -> {
      it.setValue((-2147483647));
    };
    final LiteralInteger literal = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function);
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("-2147483647", code);
  }

  @Test
  public void testEmptyLiteralBoolean() {
    final LiteralBoolean literal = this.factory.createLiteralBoolean();
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("0", code);
  }

  @Test
  public void testFalseLiteralBoolean() {
    LiteralBoolean _createLiteralBoolean = this.factory.createLiteralBoolean();
    final Procedure1<LiteralBoolean> _function = (LiteralBoolean it) -> {
      it.setValue(false);
    };
    final LiteralBoolean literal = ObjectExtensions.<LiteralBoolean>operator_doubleArrow(_createLiteralBoolean, _function);
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("0", code);
  }

  @Test
  public void testTrueLiteralBoolean() {
    LiteralBoolean _createLiteralBoolean = this.factory.createLiteralBoolean();
    final Procedure1<LiteralBoolean> _function = (LiteralBoolean it) -> {
      it.setValue(true);
    };
    final LiteralBoolean literal = ObjectExtensions.<LiteralBoolean>operator_doubleArrow(_createLiteralBoolean, _function);
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("1", code);
  }

  @Test
  public void testLiteralNull() {
    final LiteralNull literal = this.factory.createLiteralNull();
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("NULL", code);
  }

  @Test
  public void testEmptyLiteralReal() {
    final LiteralReal literal = this.factory.createLiteralReal();
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("0.0", code);
  }

  @Test
  public void testLiteralReal() {
    LiteralReal _createLiteralReal = this.factory.createLiteralReal();
    final Procedure1<LiteralReal> _function = (LiteralReal it) -> {
      it.setValue(0.3);
    };
    final LiteralReal literal = ObjectExtensions.<LiteralReal>operator_doubleArrow(_createLiteralReal, _function);
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("0.3", code);
  }

  @Test
  public void testEmptyLiteralString() {
    final LiteralString literal = this.factory.createLiteralString();
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("\"\"", code);
  }

  @Test
  public void testLiteralString() {
    LiteralString _createLiteralString = this.factory.createLiteralString();
    final Procedure1<LiteralString> _function = (LiteralString it) -> {
      it.setValue("According to all known laws of aviation, there is no way that a bee should be able to fly. Its wings are too small to get its fat little body off the ground.\n The bee, of course, flies anyways. Because bees don\'t care what humans think is impossible.");
    };
    final LiteralString literal = ObjectExtensions.<LiteralString>operator_doubleArrow(_createLiteralString, _function);
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals(
      "\"According to all known laws of aviation, there is no way that a bee should be able to fly. Its wings are too small to get its fat little body off the ground.\\n The bee, of course, flies anyways. Because bees don\'t care what humans think is impossible.\"", code);
  }
}
