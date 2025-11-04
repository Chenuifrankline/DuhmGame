package codegenerator.test.exercise3;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestProperty3 {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;
  
  @Test
  public void testEnumProperty() {
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
  
  @Test
  public void testArrayProperty2() {
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
  
  @Test
  public void testEnumInProperty() {
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
