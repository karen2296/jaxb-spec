/*
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package javax.xml.bind.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Maps a class to an XML element.
 *
 * <hr>
 * <b> Note To reviewers: </b> This annotation is derived
 * &#64;XmlElement. JAXB 2.0 ED 0.40 allowed the use of
 * &#64;XmlElement annotation on both JavaBean property and
 * class. Instead, &#64;XmlRootElement is to be used for
 * annotating the class. So examples and description that applied to
 * class have been moved from &#64;XmlElement into this annotation.
 * <hr>
 * 
 * <p> <b>Usage</b> </p>
 * <p>
 * The &#64;XmlRootElement annotation can be used with the following program
 * elements: 
 * <ul> 
 *   <li> a top level value class </li>
 * </ul>
 * <p>
 * When a top level class is annotated with the &#64;XmlRootElement annotation,
 * then the value of the class is represented as XML element in an XML
 * document.
 *
 * <p>
 * <b>Example 1: </b> Associate an element with XML Schema type
 * <pre>
 *     // Example: Code fragment
 *     &#64;XmlRootElement
 *     class Point {
 *        int x;
 *        int y;
 *        Point(int _x,int _y) {x=_x;y=_y;}
 *     }
 * </pre>
 *
 * <pre>
 *     //Example: Code fragment corresponding to XML output
 *     marshal( new Point(3,5), System.out);
 * </pre>
 *
 * <pre><xmp>
 *     <!-- Example: XML output -->
 *     <point>
 *       <x> 3 </x>
 *       <y> 5 </y>
 *     </point>
 * </xmp></pre>
 *
 * The annotation causes an global element declaration to be produced
 * in the schema. The global element declaration is associated with
 * the XML schema type to which the class is mapped.
 *
 * <pre><xmp>
 *     <!-- Example: XML schema definition -->
 *     <xs:element name="point" type="point"/>
 *     <xs:complexType name="point">
 *       <xs:sequence>
 *         <xs:element name="x" type="xs:int"/>
 *         <xs:element name="y" type="xs:int"/>
 *       </xs:sequence>
 *     </xs:complexType>
 * </xmp></pre>
 *
 * <p>
 *
 * <b>Example 2: Orthogonality to type inheritance </b>
 * 
 * <p>
 * An element declaration annotated on a type is not inherited by its
 * derived types. The following example shows this.
 * <pre>
 *     // Example: Code fragment
 *     &#64;XmlRootElement
 *     class Point3D extends Point {
 *         int z;
 *         Point(int _x,int _y,int _z) {super(_x,_y);z=_z;}
 *     }
 *
 *     //Example: Code fragment corresponding to XML output * 
 *     marshal( new Point3D(3,5,0), System.out );
 *
 *     &lt;!-- Example: XML output -->
 *     &lt;!-- The element name is point3D not point -->
 *     &lt;point3D>
 *       &lt;x>3&lt;/x>
 *       &lt;y>5&lt;/y>
 *       &lt;z>0&lt;/z>
 *     &lt;/point3D>
 *
 *     &lt;!-- Example: XML schema definition -->
 *     &lt;xs:element name="point3D" type="point3D"/>
 *     &lt;xs:complexType name="point3D">
 *       &lt;xs:complexContent>
 *         &lt;xs:extension base="point">
 *           &lt;xs:sequence>
 *             &lt;xs:element name="z" type="xs:int"/>
 *           &lt;/xs:sequence>
 *         &lt;/xs:extension>
 *       &lt;/xs:complexContent>
 *     &lt;/xs:complexType>
 * </pre>
 *
 * <b>Example 3: </b> Associate a global element with XML Schema type
 * to which the class is mapped.
 * <pre>
 *     //Example: Code fragment
 *     &#64;XmlRootElement(name="PriceElement")
 *     public class USPrice {
 *         &#64;XmlElement
 *         public java.math.BigDecimal price;
 *     }
 *
 *     &lt;!-- Example: XML schema definition -->
 *     &lt;xs:element name="PriceElement" type="USPrice"/>
 *     &lt;xs:complexType name="USPrice">
 *       &lt;xs:sequence>
 *         &lt;xs:element name="price" type="xs:decimal"/>
 *       &lt;/sequence>
 *     &lt;/xs:complexType>
 * </pre>
 *
 * @author Sekhar Vajjhala, Sun Microsystems, Inc.
 * @since JAXB2.0
 */
@Retention(RUNTIME)
@Target({TYPE})
public @interface XmlRootElement {
    /**
     * namespace name of the XML element.
     * <p>
     * If the value is "##default", then the namespace name is derived
     * from the package of the class ( {@link XmlSchema} ).
     * <p>
     * TBD: after Early Access version 0.40: Specify behavior if
     * a top level class is declared in an unnamed package.
     */
    String namespace() default "##default";

    /**
     * local name of the XML element.
     * <p>
     * If the value is "", then the name is derived from the class
     * name. 
     *
     */
    String name() default "";

}
