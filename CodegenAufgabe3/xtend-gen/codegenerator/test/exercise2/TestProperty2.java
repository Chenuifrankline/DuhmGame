package codegenerator.test.exercise2;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestProperty2 {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  /**
   * Der Name eines Attributs soll nicht durch die Klasse beeinflusst werden.
   */
  @Test
  public void test00_PropertyInClass() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("testProp");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("int");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
      EList<Property> _ownedAttributes = it.getOwnedAttributes();
      _ownedAttributes.add(property);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("int testProp;", code);
  }

  /**
   * Parameter mit einem upper-Bound größer als 1 sollen als Arrays generiert werden.
   */
  @Test
  public void test01_SimpleArrayParameter() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("testFloat");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
      it.setUpper(2);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("float testFloat[2];", code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test02_SimpleArrayParameter2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("testIntArray");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("uint32_t");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
      it.setUpper(15);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("uint32_t testIntArray[15];", code);
  }

  /**
   * Für Klassen führt dies zu einem Array von Pointern.
   */
  @Test
  public void test03_ObjectArrayParameter() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("keyboard");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("Key");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
      it.setUpper(81);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("Key* keyboard[81];", code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test04_ObjectArrayParameter2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("pacman");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("Ghost");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
      it.setUpper(4);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("Ghost* pacman[4];", code);
  }

  /**
   * Ein Upper-Bound von -1 bedeutet, dass die Anzahl unbegrenzt ist. In diesem Fall soll ein Pointer statt Array generiert werden.
   */
  @Test
  public void test05_UnlimitedListParameter() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("floatList");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
      it.setUpper((-1));
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("float* floatList;", code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test06_UnlimitedListParameter2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("listOfIteger");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("uint32_t");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
      it.setUpper((-1));
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("uint32_t* listOfIteger;", code);
  }

  /**
   * Bei Klassen führt ein unbegrenzter Upper-Bound zu einem Pointer-of-Pointer.
   */
  @Test
  public void test07_UnlimitedObjectListParameter() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("desktop");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("Icon");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
      it.setUpper((-1));
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("Icon** desktop;", code);
  }

  /**
   * Gleicher Test mit anderen Namen.
   */
  @Test
  public void test08_UnlimitedObjectListParameter2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("software");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("Bug");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
      it.setUpper((-1));
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("Bug** software;", code);
  }

  /**
   * Enumerationen sollen wie primitive Typen gehandhabt werden, was Pointer angeht.
   */
  @Test
  public void test09_EnumProperty() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("testEnum");
      Enumeration _createEnumeration = this.factory.createEnumeration();
      final Procedure1<Enumeration> _function_1 = (Enumeration it_1) -> {
        it_1.setName("AnEnum");
      };
      Enumeration _doubleArrow = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function_1);
      it.setType(_doubleArrow);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("AnEnum testEnum;", code);
  }

  /**
   * Upper-Bound + Enum = Array.
   */
  @Test
  public void test10_ArrayProperty2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("testEnumArray");
      Enumeration _createEnumeration = this.factory.createEnumeration();
      final Procedure1<Enumeration> _function_1 = (Enumeration it_1) -> {
        it_1.setName("Aufzaehlung");
      };
      Enumeration _doubleArrow = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function_1);
      it.setType(_doubleArrow);
      it.setUpper(15);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("Aufzaehlung testEnumArray[15];", code);
  }

  /**
   * Wenn ein Enum in einem Namespace liegt, muss dieser im Typename auftauchen.
   */
  @Test
  public void test11_EnumInProperty() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName("Aufzaehlung");
    };
    final Enumeration enumeration = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("pack");
      EList<Type> _ownedTypes = it.getOwnedTypes();
      _ownedTypes.add(enumeration);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function_2 = (Property it) -> {
      it.setName("testEnumArray");
      it.setType(enumeration);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function_2);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("pack_Aufzaehlung testEnumArray;", code);
  }
}
