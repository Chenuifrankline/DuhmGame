package codegenerator.test.exercise4;

import codegenerator.Uml2C;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class TestLiteralSpec2 {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  /**
   * Eine InstanceValue ohne instance ist ein nullpointer und wird als 0 generiert.
   */
  @Test
  public void test00_EmptyInstanceValue() {
    final InstanceValue literal = this.factory.createInstanceValue();
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("0", code);
  }

  /**
   * Eine InstanceValue mit instance nimmt die Adresse des Objekts.
   */
  @Test
  public void test01_InstanceValue() {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName("TestClass");
    };
    final org.eclipse.uml2.uml.Class class_ = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    InstanceSpecification _createInstanceSpecification = this.factory.createInstanceSpecification();
    final Procedure1<InstanceSpecification> _function_1 = (InstanceSpecification it) -> {
      it.setName("testInstance");
      EList<Classifier> _classifiers = it.getClassifiers();
      _classifiers.add(class_);
    };
    final InstanceSpecification inst = ObjectExtensions.<InstanceSpecification>operator_doubleArrow(_createInstanceSpecification, _function_1);
    Model _createModel = this.factory.createModel();
    final Procedure1<Model> _function_2 = (Model it) -> {
      it.setName("Model");
      EList<PackageableElement> _packagedElements = it.getPackagedElements();
      _packagedElements.add(class_);
      EList<PackageableElement> _packagedElements_1 = it.getPackagedElements();
      _packagedElements_1.add(inst);
    };
    ObjectExtensions.<Model>operator_doubleArrow(_createModel, _function_2);
    InstanceValue _createInstanceValue = this.factory.createInstanceValue();
    final Procedure1<InstanceValue> _function_3 = (InstanceValue it) -> {
      it.setInstance(inst);
    };
    final InstanceValue literal = ObjectExtensions.<InstanceValue>operator_doubleArrow(_createInstanceValue, _function_3);
    final String code = new Uml2C().generateCode(literal, "value");
    Assert.assertEquals("&Model_testInstance", code);
  }
}
