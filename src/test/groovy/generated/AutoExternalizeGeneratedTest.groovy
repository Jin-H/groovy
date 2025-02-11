package groovy.generated

import groovy.transform.CompileStatic
import org.junit.Test

@CompileStatic
class AutoExternalizeGeneratedTest extends AbstractGeneratedAstTestCase {
    final Class<?> implicitAutoExternalize = parseClass('''@groovy.transform.AutoExternalize
       class ClassUnderTest {
       }''')

    final Class<?> explicitAutoExternalize = parseClass('''@groovy.transform.AutoExternalize
       class ClassUnderTest {
           void writeExternal(ObjectOutput out) throws IOException { }
           void readExternal(ObjectInput oin) { }
       }''')

    @Test
    void test_writeExternal_is_annotated() {
        assertMethodIsAnnotated(implicitAutoExternalize, 'writeExternal', ObjectOutput)
    }

    @Test
    void test_readExternal_is_annotated() {
        assertMethodIsAnnotated(implicitAutoExternalize, 'readExternal', ObjectInput)
    }

    @Test
    void test_writeExternal_is_not_annotated() {
        assertMethodIsNotAnnotated(explicitAutoExternalize, 'writeExternal', ObjectOutput)
    }

    @Test
    void test_readExternal_is_not_annotated() {
        assertMethodIsNotAnnotated(explicitAutoExternalize, 'readExternal', ObjectInput)
    }
}