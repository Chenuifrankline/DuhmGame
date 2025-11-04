package codegenerator.test.exercise3;

import codegenerator.Uml2C;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Test;

@SuppressWarnings("all")
public class StabilityTest {
  @Extension
  private UMLFactory factory = UMLFactory.eINSTANCE;

  public <T extends Object> T randomPick(final List<T> l) {
    int _length = ((Object[])Conversions.unwrapArray(l, Object.class)).length;
    double _random = Math.random();
    double _multiply = (_length * _random);
    return l.get(((int) _multiply));
  }

  @Test
  public void testType() {
    final List<String> contexts = Arrays.<String>asList("type", "name");
    {
      int i = 0;
      boolean _while = (i < 100);
      while (_while) {
        {
          final Type umlObj = this.getRandomType(10);
          new Uml2C().generateCode(umlObj, this.<String>randomPick(contexts));
        }
        int _i = i;
        i = (_i + 1);
        _while = (i < 100);
      }
    }
  }

  @Test
  public void testProperty() {
    final List<String> contexts = Arrays.<String>asList("attribute", "name");
    {
      int i = 0;
      boolean _while = (i < 100);
      while (_while) {
        {
          final Property umlObj = this.createRandomProperty(10);
          new Uml2C().generateCode(umlObj, this.<String>randomPick(contexts));
        }
        int _i = i;
        i = (_i + 1);
        _while = (i < 100);
      }
    }
  }

  @Test
  public void testParameter() {
    final List<String> contexts = Arrays.<String>asList("parameter", "name");
    {
      int i = 0;
      boolean _while = (i < 100);
      while (_while) {
        {
          final Parameter umlObj = this.createRandomParameter(10);
          new Uml2C().generateCode(umlObj, this.<String>randomPick(contexts));
        }
        int _i = i;
        i = (_i + 1);
        _while = (i < 100);
      }
    }
  }

  @Test
  public void testOperations() {
    final List<String> contexts = Arrays.<String>asList("declaration", "implementation", "name");
    {
      int i = 0;
      boolean _while = (i < 100);
      while (_while) {
        {
          final org.eclipse.uml2.uml.Class umlObj = this.createRandomClass(10);
          new Uml2C().generateCode(umlObj, this.<String>randomPick(contexts));
        }
        int _i = i;
        i = (_i + 1);
        _while = (i < 100);
      }
    }
  }

  @Test
  public void testClass() {
    final List<String> contexts = Arrays.<String>asList("declaration", "implementation", "name");
    {
      int i = 0;
      boolean _while = (i < 100);
      while (_while) {
        {
          final org.eclipse.uml2.uml.Class umlObj = this.createRandomClass(10);
          new Uml2C().generateCode(umlObj, this.<String>randomPick(contexts));
        }
        int _i = i;
        i = (_i + 1);
        _while = (i < 100);
      }
    }
  }

  private static int counter = 0;

  private static final List<Type> types = new ArrayList<Type>();

  public String createIdentifier() {
    final String id = ("identifier" + Integer.valueOf(StabilityTest.counter));
    int _counter = StabilityTest.counter;
    StabilityTest.counter = (_counter + 1);
    return id;
  }

  public Type getRandomType(final int level) {
    if ((0 < level)) {
      if (((!StabilityTest.types.isEmpty()) && (Math.random() < 0.5))) {
        return this.<Type>randomPick(StabilityTest.types);
      }
      double _random = Math.random();
      boolean _lessThan = (_random < 0.5);
      if (_lessThan) {
        return this.createRandomPrimitiveType();
      }
      double _random_1 = Math.random();
      boolean _lessThan_1 = (_random_1 < 0.5);
      if (_lessThan_1) {
        return this.createRandomEnum();
      }
      double _random_2 = Math.random();
      boolean _lessThan_2 = (_random_2 < 0.5);
      if (_lessThan_2) {
        return this.createRandomClass((level - 1));
      }
    }
    return null;
  }

  public Parameter createRandomParameter(final int level) {
    Parameter _createParameter = this.factory.createParameter();
    final Procedure1<Parameter> _function = (Parameter it) -> {
      it.setName(this.createIdentifier());
      it.setType(this.getRandomType((level - 1)));
      final double dir = Math.random();
      if ((dir < 0.25)) {
        it.setDirection(ParameterDirectionKind.IN_LITERAL);
      } else {
        if ((dir < 0.5)) {
          it.setDirection(ParameterDirectionKind.OUT_LITERAL);
        } else {
          it.setDirection(ParameterDirectionKind.INOUT_LITERAL);
        }
      }
    };
    return ObjectExtensions.<Parameter>operator_doubleArrow(_createParameter, _function);
  }

  public Property createRandomProperty(final int level) {
    Property _createProperty = this.factory.createProperty();
    final Procedure1<Property> _function = (Property it) -> {
      it.setName(this.createIdentifier());
      it.setType(this.getRandomType((level - 1)));
      final double dir = Math.random();
      if ((dir < 0.5)) {
        it.setAggregation(AggregationKind.NONE_LITERAL);
      } else {
        it.setAggregation(AggregationKind.COMPOSITE_LITERAL);
      }
      double _random = Math.random();
      boolean _lessThan = (_random < 0.25);
      it.setIsStatic(_lessThan);
    };
    return ObjectExtensions.<Property>operator_doubleArrow(_createProperty, _function);
  }

  public Operation createRandomOperation(final int level) {
    Operation _createOperation = this.factory.createOperation();
    final Procedure1<Operation> _function = (Operation it) -> {
      it.setName(this.createIdentifier());
      double _random = Math.random();
      double _multiply = (_random * 10);
      final int numParams = ((int) _multiply);
      {
        int i = 0;
        boolean _while = (i < numParams);
        while (_while) {
          EList<Parameter> _ownedParameters = it.getOwnedParameters();
          Parameter _createRandomParameter = this.createRandomParameter((level - 1));
          _ownedParameters.add(_createRandomParameter);
          int _i = i;
          i = (_i + 1);
          _while = (i < numParams);
        }
      }
      double _random_1 = Math.random();
      boolean _lessThan = (_random_1 < 0.25);
      it.setIsStatic(_lessThan);
      EList<Behavior> _methods = it.getMethods();
      OpaqueBehavior _createOpaqueBehavior = this.factory.createOpaqueBehavior();
      final Procedure1<OpaqueBehavior> _function_1 = (OpaqueBehavior it_1) -> {
        EList<String> _languages = it_1.getLanguages();
        _languages.add("C");
        EList<String> _bodies = it_1.getBodies();
        _bodies.add("/*impl*/");
      };
      OpaqueBehavior _doubleArrow = ObjectExtensions.<OpaqueBehavior>operator_doubleArrow(_createOpaqueBehavior, _function_1);
      _methods.add(_doubleArrow);
    };
    return ObjectExtensions.<Operation>operator_doubleArrow(_createOperation, _function);
  }

  public PrimitiveType createRandomPrimitiveType() {
    PrimitiveType _createPrimitiveType = this.factory.createPrimitiveType();
    final Procedure1<PrimitiveType> _function = (PrimitiveType it) -> {
      it.setName(this.createIdentifier());
    };
    final PrimitiveType primitive = ObjectExtensions.<PrimitiveType>operator_doubleArrow(_createPrimitiveType, _function);
    StabilityTest.types.add(primitive);
    return primitive;
  }

  public Enumeration createRandomEnum() {
    Enumeration _createEnumeration = this.factory.createEnumeration();
    final Procedure1<Enumeration> _function = (Enumeration it) -> {
      it.setName(this.createIdentifier());
      double _random = Math.random();
      double _multiply = (_random * 10);
      final int numLits = ((int) _multiply);
      {
        int i = 0;
        boolean _while = (i < numLits);
        while (_while) {
          EList<EnumerationLiteral> _ownedLiterals = it.getOwnedLiterals();
          EnumerationLiteral _createEnumerationLiteral = this.factory.createEnumerationLiteral();
          final Procedure1<EnumerationLiteral> _function_1 = (EnumerationLiteral it_1) -> {
            it_1.setName(this.createIdentifier());
          };
          EnumerationLiteral _doubleArrow = ObjectExtensions.<EnumerationLiteral>operator_doubleArrow(_createEnumerationLiteral, _function_1);
          _ownedLiterals.add(_doubleArrow);
          int _i = i;
          i = (_i + 1);
          _while = (i < numLits);
        }
      }
    };
    final Enumeration enum_ = ObjectExtensions.<Enumeration>operator_doubleArrow(_createEnumeration, _function);
    StabilityTest.types.add(enum_);
    return enum_;
  }

  public org.eclipse.uml2.uml.Package createNamespace(final int level) {
    org.eclipse.uml2.uml.Package _createPackage = this.factory.createPackage();
    final Procedure1<org.eclipse.uml2.uml.Package> _function = (org.eclipse.uml2.uml.Package it) -> {
      it.setName(this.createIdentifier());
    };
    final org.eclipse.uml2.uml.Package pack = ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createPackage, _function);
    if (((0 < level) && (Math.random() < 0.1))) {
      org.eclipse.uml2.uml.Package _createNamespace = this.createNamespace((level - 1));
      final Procedure1<org.eclipse.uml2.uml.Package> _function_1 = (org.eclipse.uml2.uml.Package it) -> {
        EList<NamedElement> _ownedMembers = it.getOwnedMembers();
        _ownedMembers.add(pack);
      };
      ObjectExtensions.<org.eclipse.uml2.uml.Package>operator_doubleArrow(_createNamespace, _function_1);
    }
    return pack;
  }

  public org.eclipse.uml2.uml.Class createRandomClass(final int level) {
    org.eclipse.uml2.uml.Class _createClass = this.factory.createClass();
    final Procedure1<org.eclipse.uml2.uml.Class> _function = (org.eclipse.uml2.uml.Class it) -> {
      it.setName(this.createIdentifier());
      double _random = Math.random();
      double _multiply = (_random * 10);
      final int numProps = ((int) _multiply);
      {
        int i = 0;
        boolean _while = (i < numProps);
        while (_while) {
          EList<Property> _ownedAttributes = it.getOwnedAttributes();
          Property _createRandomProperty = this.createRandomProperty((level - 1));
          _ownedAttributes.add(_createRandomProperty);
          int _i = i;
          i = (_i + 1);
          _while = (i < numProps);
        }
      }
      double _random_1 = Math.random();
      double _multiply_1 = (_random_1 * 10);
      final int numOps = ((int) _multiply_1);
      {
        int i = 0;
        boolean _while = (i < numOps);
        while (_while) {
          {
            final Operation op = this.createRandomOperation((level - 1));
            EList<Operation> _ownedOperations = it.getOwnedOperations();
            _ownedOperations.add(op);
            EList<Behavior> _ownedBehaviors = it.getOwnedBehaviors();
            EList<Behavior> _methods = op.getMethods();
            Iterables.<Behavior>addAll(_ownedBehaviors, _methods);
          }
          int _i = i;
          i = (_i + 1);
          _while = (i < numOps);
        }
      }
    };
    final org.eclipse.uml2.uml.Class clazz = ObjectExtensions.<org.eclipse.uml2.uml.Class>operator_doubleArrow(_createClass, _function);
    StabilityTest.types.add(clazz);
    return clazz;
  }
}
