package codegenerator.test.exercise1;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.PackageableElement;
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
public class TestProperty {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  /**
   * Ein einfaches Property mit Name und Datentyp.
   */
  @Test
  public void test00_SimpleTypedProperty() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("testProperty");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("uint32");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("uint32 testProperty;", code);
  }

  /**
   * Wie vorher, mit anderem Namen und Datentyp.
   */
  @Test
  public void test01_SimpleTypedProperty2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("testFloat");
      PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
      final Procedure1<PrimitiveType> _function_1 = (PrimitiveType it_1) -> {
        it_1.setName("float");
      };
      PrimitiveType _doubleArrow = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function_1);
      it.setType(_doubleArrow);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("float testFloat;", code);
  }

  /**
   * Bei Properties mit einer Klasse als Datentyp soll ein Pointer generiert werden.
   */
  @Test
  public void test02_ComplexTypedProperty() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("propertyTest");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("TestClass");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("TestClass* propertyTest;", code);
  }

  /**
   * Wie vorher, mit geändertem Namen und Datentyp.
   */
  @Test
  public void test03_ComplexTypedProperty2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("complexTestProperty");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("AnotherClass");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("AnotherClass* complexTestProperty;", code);
  }

  /**
   * Klassen können in einem Namespace liegen, dieser soll mit an den Namen der Klasse generiert werden.
   */
  @Test
  public void test04_NamespacedComplexTypedProperty() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("propertyTest");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("TestClass");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("testPackage");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      Type _type = property.getType();
      _packagedElements.add(_type);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("testPackage_TestClass* propertyTest;", code);
  }

  /**
   * Wie vorher, mit geändertem Namen und Datentyp.
   */
  @Test
  public void test05_NamespacedComplexTypedProperty2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("someAttribute");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("TestType");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
      it.setName("aPackage");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      Type _type = property.getType();
      _packagedElements.add(_type);
    };
    ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function_1);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("aPackage_TestType* someAttribute;", code);
  }

  /**
   * Ein Property ohne Datentyp soll "void*" als Typ generiert bekommen.
   */
  @Test
  public void test06_UntypedProperty() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("untypedTestProperty");
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("void* untypedTestProperty;", code);
  }

  /**
   * Ist ein Property eine Komposition, soll auch für Klassen kein Pointer generiert werden.
   * Komposition wird durch das Attribut "aggregation" mit dem Wert "AggregationKind.COMPOSITE_LITERAL" gekennzeichnet.
   * Der Standardwert für "aggregation" ist "AggregationKind.NONE_LITERAL".
   */
  @Test
  public void test07_OwnedComplexTypedProperty() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("testAttribute");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("testClass");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
      it.setAggregation(AggregationKind.COMPOSITE_LITERAL);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("testClass testAttribute;", code);
  }

  /**
   * Wie vorher, mit geändertem Datentyp.
   */
  @Test
  public void test08_OwnedComplexTypedProperty2() {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName("ownedAttributeTest");
      org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
      final Procedure1<org.eclipse.uml2.uml.Class> _function_1 = (org.eclipse.uml2.uml.Class it_1) -> {
        it_1.setName("ClassTest");
      };
      org.eclipse.uml2.uml.Class _doubleArrow = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function_1);
      it.setType(_doubleArrow);
      it.setAggregation(AggregationKind.COMPOSITE_LITERAL);
    };
    final Property property = ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
    final String code = new Uml2C().generateCode(property, "attribute");
    Assert.assertEquals("ClassTest ownedAttributeTest;", code);
  }
}
