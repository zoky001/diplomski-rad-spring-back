//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.klijent.servis.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for answer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="answer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="question" type="{http://service.zok.zaba.hr/}question" minOccurs="0"/>
 *         &lt;element name="type" type="{http://service.zok.zaba.hr/}answerType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "answer", propOrder = {
    "id",
    "question",
    "type"
})
@XmlSeeAlso({
    ParticipationInOwnershipSingleAnswer.class
})
public abstract class Answer {

    protected Long id;
    protected Question question;
    protected AnswerType type;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the question property.
     * 
     * @return
     *     possible object is
     *     {@link Question }
     *     
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Sets the value of the question property.
     * 
     * @param value
     *     allowed object is
     *     {@link Question }
     *     
     */
    public void setQuestion(Question value) {
        this.question = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link AnswerType }
     *     
     */
    public AnswerType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnswerType }
     *     
     */
    public void setType(AnswerType value) {
        this.type = value;
    }

}
