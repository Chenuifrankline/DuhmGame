package codegenerator.test;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestEnumLiteral {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  @Test
  public void testSimpleLiteral() {
    EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
    final Procedure1<EnumerationLiteral> _function = (EnumerationLiteral it) -> {
      it.setName("testLiteral");
    };
    final EnumerationLiteral literal = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function);
    final String code = new Uml2C().generateCode(literal, "enumliteral");
    Assert.assertEquals("testLiteral", code);
  }

  @Test
  public void testLiteralInEnum() {
    EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
    final Procedure1<EnumerationLiteral> _function = (EnumerationLiteral it) -> {
      it.setName("testLiteral2");
    };
    final EnumerationLiteral literal = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function);
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function_1 = (Enumeration it) -> {
      it.setName("TestEnum");
      EList<EnumerationLiteral> _ownedLiterals = it.getOwnedLiterals();
      _ownedLiterals.add(literal);
    };
    ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function_1);
    final String code = new Uml2C().generateCode(literal, "enumliteral");
    Assert.assertEquals("TestEnum_testLiteral2", code);
  }

  @Test
  public void testSimpleLiteralWithValue() {
    EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
    final Procedure1<EnumerationLiteral> _function = (EnumerationLiteral it) -> {
      it.setName("literalTest");
      LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
      final Procedure1<LiteralInteger> _function_1 = (LiteralInteger it_1) -> {
        it_1.setValue(42);
      };
      LiteralInteger _doubleArrow = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_1);
      it.setSpecification(_doubleArrow);
    };
    final EnumerationLiteral literal = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function);
    final String code = new Uml2C().generateCode(literal, "enumliteral");
    Assert.assertEquals("literalTest = 42", code);
  }

  @Test
  public void testLiteralInEnumWithValue() {
    EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
    final Procedure1<EnumerationLiteral> _function = (EnumerationLiteral it) -> {
      it.setName("literalTest2");
      LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
      final Procedure1<LiteralInteger> _function_1 = (LiteralInteger it_1) -> {
        it_1.setValue(1337);
      };
      LiteralInteger _doubleArrow = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_1);
      it.setSpecification(_doubleArrow);
    };
    final EnumerationLiteral literal = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function);
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function_1 = (Enumeration it) -> {
      it.setName("EnumTest");
      EList<EnumerationLiteral> _ownedLiterals = it.getOwnedLiterals();
      _ownedLiterals.add(literal);
    };
    ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function_1);
    final String code = new Uml2C().generateCode(literal, "enumliteral");
    Assert.assertEquals("EnumTest_literalTest2 = 1337", code);
  }
}
