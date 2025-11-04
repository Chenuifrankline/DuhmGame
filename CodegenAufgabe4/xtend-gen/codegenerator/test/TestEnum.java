package codegenerator.test;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestEnum {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  @Test
  public void testEmptyEnum() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName("TestEnum");
    };
    final Enumeration enumeration = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    final String code = new Uml2C().generateCode(enumeration, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef enum {");
    _builder.newLine();
    _builder.append("} TestEnum;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  @Test
  public void testEnumWithOneLiteral() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName("TestEnum");
      EList<EnumerationLiteral> _ownedLiterals = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_1 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalTest");
      };
      EnumerationLiteral _doubleArrow = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function_1);
      _ownedLiterals.add(_doubleArrow);
    };
    final Enumeration enumeration = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    final String code = new Uml2C().generateCode(enumeration, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef enum {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnum_literalTest");
    _builder.newLine();
    _builder.append("} TestEnum;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  @Test
  public void testEnumWithOneLiteralWithValue() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName("EnumTest");
      EList<EnumerationLiteral> _ownedLiterals = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_1 = (EnumerationLiteral it_1) -> {
        it_1.setName("testLiteral");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_2 = (LiteralInteger it_2) -> {
          it_2.setValue(1234);
        };
        LiteralInteger _doubleArrow = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_2);
        it_1.setSpecification(_doubleArrow);
      };
      EnumerationLiteral _doubleArrow = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function_1);
      _ownedLiterals.add(_doubleArrow);
    };
    final Enumeration enumeration = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    final String code = new Uml2C().generateCode(enumeration, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef enum {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("EnumTest_testLiteral = 1234");
    _builder.newLine();
    _builder.append("} EnumTest;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  @Test
  public void testEnumWithMoreLiterals() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName("TestEnumeration");
      EList<EnumerationLiteral> _ownedLiterals = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_1 = (EnumerationLiteral it_1) -> {
        it_1.setName("literal0");
      };
      EnumerationLiteral _doubleArrow = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function_1);
      _ownedLiterals.add(_doubleArrow);
      EList<EnumerationLiteral> _ownedLiterals_1 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_1 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_2 = (EnumerationLiteral it_1) -> {
        it_1.setName("literal1");
      };
      EnumerationLiteral _doubleArrow_1 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_1, _function_2);
      _ownedLiterals_1.add(_doubleArrow_1);
      EList<EnumerationLiteral> _ownedLiterals_2 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_2 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_3 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalZwei");
      };
      EnumerationLiteral _doubleArrow_2 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_2, _function_3);
      _ownedLiterals_2.add(_doubleArrow_2);
      EList<EnumerationLiteral> _ownedLiterals_3 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_3 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_4 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalThree");
      };
      EnumerationLiteral _doubleArrow_3 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_3, _function_4);
      _ownedLiterals_3.add(_doubleArrow_3);
    };
    final Enumeration enumeration = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    final String code = new Uml2C().generateCode(enumeration, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef enum {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literal0,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literal1,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalZwei,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalThree");
    _builder.newLine();
    _builder.append("} TestEnumeration;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  @Test
  public void testEnumWithMoreLiteralsWithValues() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName("TestEnumeration");
      EList<EnumerationLiteral> _ownedLiterals = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_1 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalNull");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_2 = (LiteralInteger it_2) -> {
          it_2.setValue(24601);
        };
        LiteralInteger _doubleArrow = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_2);
        it_1.setSpecification(_doubleArrow);
      };
      EnumerationLiteral _doubleArrow = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function_1);
      _ownedLiterals.add(_doubleArrow);
      EList<EnumerationLiteral> _ownedLiterals_1 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_1 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_2 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalEins");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_3 = (LiteralInteger it_2) -> {
          it_2.setValue(1);
        };
        LiteralInteger _doubleArrow_1 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_3);
        it_1.setSpecification(_doubleArrow_1);
      };
      EnumerationLiteral _doubleArrow_1 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_1, _function_2);
      _ownedLiterals_1.add(_doubleArrow_1);
      EList<EnumerationLiteral> _ownedLiterals_2 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_2 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_3 = (EnumerationLiteral it_1) -> {
        it_1.setName("literal2");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_4 = (LiteralInteger it_2) -> {
          it_2.setValue((-1));
        };
        LiteralInteger _doubleArrow_2 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_4);
        it_1.setSpecification(_doubleArrow_2);
      };
      EnumerationLiteral _doubleArrow_2 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_2, _function_3);
      _ownedLiterals_2.add(_doubleArrow_2);
      EList<EnumerationLiteral> _ownedLiterals_3 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_3 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_4 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalIII");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_5 = (LiteralInteger it_2) -> {
          it_2.setValue(420);
        };
        LiteralInteger _doubleArrow_3 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_5);
        it_1.setSpecification(_doubleArrow_3);
      };
      EnumerationLiteral _doubleArrow_3 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_3, _function_4);
      _ownedLiterals_3.add(_doubleArrow_3);
    };
    final Enumeration enumeration = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    final String code = new Uml2C().generateCode(enumeration, "typedefinition");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("typedef enum {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalNull = 24601,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalEins = 1,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literal2 = -1,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalIII = 420");
    _builder.newLine();
    _builder.append("} TestEnumeration;");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  @Test
  public void testEnumHeaderWithMoreLiteralsWithValues() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName("TestEnumeration");
      EList<EnumerationLiteral> _ownedLiterals = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_1 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalNull");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_2 = (LiteralInteger it_2) -> {
          it_2.setValue(24601);
        };
        LiteralInteger _doubleArrow = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_2);
        it_1.setSpecification(_doubleArrow);
      };
      EnumerationLiteral _doubleArrow = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function_1);
      _ownedLiterals.add(_doubleArrow);
      EList<EnumerationLiteral> _ownedLiterals_1 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_1 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_2 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalEins");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_3 = (LiteralInteger it_2) -> {
          it_2.setValue(1);
        };
        LiteralInteger _doubleArrow_1 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_3);
        it_1.setSpecification(_doubleArrow_1);
      };
      EnumerationLiteral _doubleArrow_1 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_1, _function_2);
      _ownedLiterals_1.add(_doubleArrow_1);
      EList<EnumerationLiteral> _ownedLiterals_2 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_2 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_3 = (EnumerationLiteral it_1) -> {
        it_1.setName("literal2");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_4 = (LiteralInteger it_2) -> {
          it_2.setValue((-1));
        };
        LiteralInteger _doubleArrow_2 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_4);
        it_1.setSpecification(_doubleArrow_2);
      };
      EnumerationLiteral _doubleArrow_2 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_2, _function_3);
      _ownedLiterals_2.add(_doubleArrow_2);
      EList<EnumerationLiteral> _ownedLiterals_3 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_3 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_4 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalIII");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_5 = (LiteralInteger it_2) -> {
          it_2.setValue(420);
        };
        LiteralInteger _doubleArrow_3 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_5);
        it_1.setSpecification(_doubleArrow_3);
      };
      EnumerationLiteral _doubleArrow_3 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_3, _function_4);
      _ownedLiterals_3.add(_doubleArrow_3);
    };
    final Enumeration enumeration = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    final String code = new Uml2C().generateCode(enumeration, "declaration");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#ifndef TESTENUMERATION_H");
    _builder.newLine();
    _builder.append("#define TESTENUMERATION_H");
    _builder.newLine();
    _builder.newLine();
    _builder.append("typedef enum {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalNull = 24601,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalEins = 1,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literal2 = -1,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalIII = 420");
    _builder.newLine();
    _builder.append("} TestEnumeration;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("extern TestEnumeration TestEnumeration_Literals[4];");
    _builder.newLine();
    _builder.newLine();
    _builder.append("#endif");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }

  @Test
  public void testEnumImplementationWithMoreLiteralsWithValues() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName("TestEnumeration");
      EList<EnumerationLiteral> _ownedLiterals = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_1 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalNull");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_2 = (LiteralInteger it_2) -> {
          it_2.setValue(24601);
        };
        LiteralInteger _doubleArrow = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_2);
        it_1.setSpecification(_doubleArrow);
      };
      EnumerationLiteral _doubleArrow = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function_1);
      _ownedLiterals.add(_doubleArrow);
      EList<EnumerationLiteral> _ownedLiterals_1 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_1 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_2 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalEins");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_3 = (LiteralInteger it_2) -> {
          it_2.setValue(1);
        };
        LiteralInteger _doubleArrow_1 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_3);
        it_1.setSpecification(_doubleArrow_1);
      };
      EnumerationLiteral _doubleArrow_1 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_1, _function_2);
      _ownedLiterals_1.add(_doubleArrow_1);
      EList<EnumerationLiteral> _ownedLiterals_2 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_2 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_3 = (EnumerationLiteral it_1) -> {
        it_1.setName("literal2");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_4 = (LiteralInteger it_2) -> {
          it_2.setValue((-1));
        };
        LiteralInteger _doubleArrow_2 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_4);
        it_1.setSpecification(_doubleArrow_2);
      };
      EnumerationLiteral _doubleArrow_2 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_2, _function_3);
      _ownedLiterals_2.add(_doubleArrow_2);
      EList<EnumerationLiteral> _ownedLiterals_3 = it.getOwnedLiterals();
      EnumerationLiteral _createEnumerationLiteral_3 = this.factory.createEnumerationLiteral();
      final Procedure1<EnumerationLiteral> _function_4 = (EnumerationLiteral it_1) -> {
        it_1.setName("literalIII");
        LiteralInteger _createLiteralInteger = this.factory.createLiteralInteger();
        final Procedure1<LiteralInteger> _function_5 = (LiteralInteger it_2) -> {
          it_2.setValue(420);
        };
        LiteralInteger _doubleArrow_3 = ObjectExtensions.<LiteralInteger>operator_doubleArrow(_createLiteralInteger, _function_5);
        it_1.setSpecification(_doubleArrow_3);
      };
      EnumerationLiteral _doubleArrow_3 = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral_3, _function_4);
      _ownedLiterals_3.add(_doubleArrow_3);
    };
    final Enumeration enumeration = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    final String code = new Uml2C().generateCode(enumeration, "implementation");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"TestEnumeration.h\"");
    _builder.newLine();
    _builder.newLine();
    _builder.append("TestEnumeration TestEnumeration_Literals[4] = {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalNull,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalEins,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literal2,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TestEnumeration_literalIII");
    _builder.newLine();
    _builder.append("};");
    _builder.newLine();
    Assert.assertEquals(_builder.toString(), code);
  }
}
