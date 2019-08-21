package hr.foi.diplomski.rad.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.codehaus.groovy.runtime.BytecodeInterface8
import org.codehaus.groovy.runtime.InvokerHelper
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter
import org.codehaus.groovy.runtime.callsite.CallSite
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation
import org.codehaus.groovy.runtime.typehandling.ShortTypeHandling
import org.codehaus.groovy.util.HashCodeHelper

@ToString(
        includeNames = true,
        includePackage = false
)
@EqualsAndHashCode
public class ServiceResult implements Serializable, GroovyObject {
    private static final long serialVersionUID = 1L;
    private boolean success;
    private boolean mandatory;
    private Object result;
    private Object commonData;
    private String secureToken;
    private List<String> errorMessageCodeList;
    private List<String> errorMessageTextList;

    public ServiceResult() {
        CallSite[] var1 = $getCallSiteArray();
        boolean var2 = true;
        this.mandatory = var2;
        List var3 = ScriptBytecodeAdapter.createList(new Object[0]);
        this.errorMessageCodeList = var3;
        List var4 = ScriptBytecodeAdapter.createList(new Object[0]);
        this.errorMessageTextList = var4;
        MetaClass var5 = this.$getStaticMetaClass();
        this.metaClass = var5;
    }

/*
    public ServiceResult mergeWith(ServiceResult other, List resultNameList, List commonDataNameList) {
        CallSite[] var4 = $getCallSiteArray();
        var4[0].call(Assert.class, ScriptBytecodeAdapter.compareEqual(var4[1].callSafe(resultNameList), 2), "Result name must be defined and have size 2");
        var4[2].call(Assert.class, ScriptBytecodeAdapter.compareEqual(var4[3].callSafe(commonDataNameList), 2), "Common data name list must be defined and have size 2");
        Object mergedResult = ScriptBytecodeAdapter.createMap(new Object[0]);
        Object mergedCommonData = ScriptBytecodeAdapter.createMap(new Object[0]);
        Object mergedSuccess = null;
        if (BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            boolean var9 = (this.success || !this.mandatory) && (DefaultTypeTransformation.booleanUnbox(var4[7].callGroovyObjectGetProperty(other)) || !DefaultTypeTransformation.booleanUnbox(var4[8].callGroovyObjectGetProperty(other))) && (this.success || DefaultTypeTransformation.booleanUnbox(var4[9].callGroovyObjectGetProperty(other)));
            mergedSuccess = var9;
        } else {
            boolean var8 = (this.success || !this.mandatory) && (DefaultTypeTransformation.booleanUnbox(var4[4].callGroovyObjectGetProperty(other)) || !DefaultTypeTransformation.booleanUnbox(var4[5].callGroovyObjectGetProperty(other))) && (this.success || DefaultTypeTransformation.booleanUnbox(var4[6].callGroovyObjectGetProperty(other)));
            mergedSuccess = var8;
        }

        Object mergedMandatory = this.mandatory || DefaultTypeTransformation.booleanUnbox(var4[10].callGroovyObjectGetProperty(other));
        if (this.result instanceof Map && var4[11].callGroovyObjectGetProperty(other) instanceof Map) {
            var4[12].call(var4[13].call(mergedResult, this.result), var4[14].callGroovyObjectGetProperty(other));
        } else if (DefaultTypeTransformation.booleanUnbox(this.result) || DefaultTypeTransformation.booleanUnbox(var4[15].callGroovyObjectGetProperty(other))) {
            var4[16].call(var4[17].call(mergedResult, ScriptBytecodeAdapter.createMap(new Object[]{var4[18].call(resultNameList, 0), this.result})), ScriptBytecodeAdapter.createMap(new Object[]{var4[19].call(resultNameList, 1), var4[20].callGroovyObjectGetProperty(other)}));
        }

        if (this.commonData instanceof Map && var4[21].callGroovyObjectGetProperty(other) instanceof Map) {
            var4[22].call(var4[23].call(mergedCommonData, this.commonData), var4[24].callGroovyObjectGetProperty(other));
        } else if (DefaultTypeTransformation.booleanUnbox(this.commonData) || DefaultTypeTransformation.booleanUnbox(var4[25].callGroovyObjectGetProperty(other))) {
            var4[26].call(var4[27].call(mergedCommonData, ScriptBytecodeAdapter.createMap(new Object[]{var4[28].call(commonDataNameList, 0), this.commonData})), ScriptBytecodeAdapter.createMap(new Object[]{var4[29].call(commonDataNameList, 1), var4[30].callGroovyObjectGetProperty(other)}));
        }

        Object mergedErrorMessageCodeList = ScriptBytecodeAdapter.createList(new Object[0]);
        if (DefaultTypeTransformation.booleanUnbox(this.errorMessageCodeList)) {
            var4[31].call(mergedErrorMessageCodeList, this.errorMessageCodeList);
        }

        if (DefaultTypeTransformation.booleanUnbox(var4[32].callGroovyObjectGetProperty(other))) {
            var4[33].call(mergedErrorMessageCodeList, var4[34].callGroovyObjectGetProperty(other));
        }

        Object var12 = var4[35].call(var4[36].call(mergedErrorMessageCodeList));
        Object mergedErrorMessageTextList = ScriptBytecodeAdapter.createList(new Object[0]);
        if (DefaultTypeTransformation.booleanUnbox(this.errorMessageTextList)) {
            var4[37].call(mergedErrorMessageTextList, this.errorMessageTextList);
        }

        if (DefaultTypeTransformation.booleanUnbox(var4[38].callGroovyObjectGetProperty(other))) {
            var4[39].call(mergedErrorMessageTextList, var4[40].callGroovyObjectGetProperty(other));
        }

        Object var14 = var4[41].call(var4[42].call(mergedErrorMessageTextList));
        return (ServiceResult) ScriptBytecodeAdapter.castToType(var4[43].callConstructor(ServiceResult.class, ScriptBytecodeAdapter.createMap(new Object[]{"success", mergedSuccess, "mandatory", mergedMandatory, "errorMessageCodeList", var12, "errorMessageTextList", var14, "result", mergedResult, "commonData", mergedCommonData})), ServiceResult.class);
    }

    public static ServiceResult mergeAll(List<ServiceResult> serviceResultList) {
        CallSite[] var1 = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareLessThan(var1[44].call(serviceResultList), 1)) {
            throw (Throwable) var1[45].callConstructor(IllegalArgumentException.class, "At least one ServiceResult has to be present");
        } else {
            final Reference mergedServiceResult = new Reference(var1[46].call(serviceResultList, 0));

            class _mergeAll_closure1 extends Closure implements GeneratedClosure {
                public _mergeAll_closure1(Object _outerInstance, Object _thisObject) {
                    CallSite[] var4 = $getCallSiteArray();
                    super(_outerInstance, _thisObject);
                }

                public Object doCall(Object serviceResult) {
                    CallSite[] var2 = $getCallSiteArray();
                    Object var3 = var2[0].call(mergedServiceResult.get(), serviceResult);
                    mergedServiceResult.set(var3);
                    return var3;
                }

                public Object getMergedServiceResult() {
                    CallSite[] var1 = $getCallSiteArray();
                    return mergedServiceResult.get();
                }
            }

            var1[47].call(serviceResultList, new _mergeAll_closure1(ServiceResult.class, ServiceResult.class));
            return (ServiceResult) ScriptBytecodeAdapter.castToType(mergedServiceResult.get(), ServiceResult.class);
        }
    }
*/

    public String toString() {
        CallSite[] var1 = $getCallSiteArray();
        Object _result = var1[48].callConstructor(StringBuilder.class);
        Object $toStringFirst = Boolean.TRUE;
        var1[49].call(_result, "ServiceResult(");
        if (DefaultTypeTransformation.booleanUnbox($toStringFirst)) {
            Boolean var4 = Boolean.FALSE;
            $toStringFirst = var4;
        } else {
            var1[50].call(_result, ", ");
        }

        var1[51].call(_result, "success:");
        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(var1[58].call(this.isSuccess(), this))) {
                var1[59].call(_result, "(this)");
            } else {
                var1[60].call(_result, var1[61].callStatic(InvokerHelper.class, this.isSuccess()));
            }
        } else if (DefaultTypeTransformation.booleanUnbox(var1[52].call(var1[53].callCurrent(this), this))) {
            var1[54].call(_result, "(this)");
        } else {
            var1[55].call(_result, var1[56].callStatic(InvokerHelper.class, var1[57].callCurrent(this)));
        }

        if (DefaultTypeTransformation.booleanUnbox($toStringFirst)) {
            Boolean var5 = Boolean.FALSE;
            $toStringFirst = var5;
        } else {
            var1[62].call(_result, ", ");
        }

        var1[63].call(_result, "mandatory:");
        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(var1[70].call(this.isMandatory(), this))) {
                var1[71].call(_result, "(this)");
            } else {
                var1[72].call(_result, var1[73].callStatic(InvokerHelper.class, this.isMandatory()));
            }
        } else if (DefaultTypeTransformation.booleanUnbox(var1[64].call(var1[65].callCurrent(this), this))) {
            var1[66].call(_result, "(this)");
        } else {
            var1[67].call(_result, var1[68].callStatic(InvokerHelper.class, var1[69].callCurrent(this)));
        }

        if (DefaultTypeTransformation.booleanUnbox($toStringFirst)) {
            Boolean var6 = Boolean.FALSE;
            $toStringFirst = var6;
        } else {
            var1[74].call(_result, ", ");
        }

        var1[75].call(_result, "result:");
        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(var1[82].call(this.getResult(), this))) {
                var1[83].call(_result, "(this)");
            } else {
                var1[84].call(_result, var1[85].callStatic(InvokerHelper.class, this.getResult()));
            }
        } else if (DefaultTypeTransformation.booleanUnbox(var1[76].call(var1[77].callCurrent(this), this))) {
            var1[78].call(_result, "(this)");
        } else {
            var1[79].call(_result, var1[80].callStatic(InvokerHelper.class, var1[81].callCurrent(this)));
        }

        if (DefaultTypeTransformation.booleanUnbox($toStringFirst)) {
            Boolean var7 = Boolean.FALSE;
            $toStringFirst = var7;
        } else {
            var1[86].call(_result, ", ");
        }

        var1[87].call(_result, "commonData:");
        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(var1[94].call(this.getCommonData(), this))) {
                var1[95].call(_result, "(this)");
            } else {
                var1[96].call(_result, var1[97].callStatic(InvokerHelper.class, this.getCommonData()));
            }
        } else if (DefaultTypeTransformation.booleanUnbox(var1[88].call(var1[89].callCurrent(this), this))) {
            var1[90].call(_result, "(this)");
        } else {
            var1[91].call(_result, var1[92].callStatic(InvokerHelper.class, var1[93].callCurrent(this)));
        }

        if (DefaultTypeTransformation.booleanUnbox($toStringFirst)) {
            Boolean var8 = Boolean.FALSE;
            $toStringFirst = var8;
        } else {
            var1[98].call(_result, ", ");
        }

        var1[99].call(_result, "secureToken:");
        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(var1[106].call(this.getSecureToken(), this))) {
                var1[107].call(_result, "(this)");
            } else {
                var1[108].call(_result, var1[109].callStatic(InvokerHelper.class, this.getSecureToken()));
            }
        } else if (DefaultTypeTransformation.booleanUnbox(var1[100].call(var1[101].callCurrent(this), this))) {
            var1[102].call(_result, "(this)");
        } else {
            var1[103].call(_result, var1[104].callStatic(InvokerHelper.class, var1[105].callCurrent(this)));
        }

        if (DefaultTypeTransformation.booleanUnbox($toStringFirst)) {
            Boolean var9 = Boolean.FALSE;
            $toStringFirst = var9;
        } else {
            var1[110].call(_result, ", ");
        }

        var1[111].call(_result, "errorMessageCodeList:");
        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(var1[118].call(this.getErrorMessageCodeList(), this))) {
                var1[119].call(_result, "(this)");
            } else {
                var1[120].call(_result, var1[121].callStatic(InvokerHelper.class, this.getErrorMessageCodeList()));
            }
        } else if (DefaultTypeTransformation.booleanUnbox(var1[112].call(var1[113].callCurrent(this), this))) {
            var1[114].call(_result, "(this)");
        } else {
            var1[115].call(_result, var1[116].callStatic(InvokerHelper.class, var1[117].callCurrent(this)));
        }

        if (DefaultTypeTransformation.booleanUnbox($toStringFirst)) {
            Boolean var10 = Boolean.FALSE;
        } else {
            var1[122].call(_result, ", ");
        }

        var1[123].call(_result, "errorMessageTextList:");
        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (DefaultTypeTransformation.booleanUnbox(var1[130].call(this.getErrorMessageTextList(), this))) {
                var1[131].call(_result, "(this)");
            } else {
                var1[132].call(_result, var1[133].callStatic(InvokerHelper.class, this.getErrorMessageTextList()));
            }
        } else if (DefaultTypeTransformation.booleanUnbox(var1[124].call(var1[125].callCurrent(this), this))) {
            var1[126].call(_result, "(this)");
        } else {
            var1[127].call(_result, var1[128].callStatic(InvokerHelper.class, var1[129].callCurrent(this)));
        }

        var1[134].call(_result, ")");
        return (String) ShortTypeHandling.castToString(var1[135].call(_result));
    }

    public int hashCode() {
        CallSite[] var1 = $getCallSiteArray();
        Object _result = var1[136].callStatic(HashCodeHelper.class);
        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(var1[141].call(this.isSuccess(), this))) {
                Object var4 = var1[142].callStatic(HashCodeHelper.class, _result, this.isSuccess());
                _result = var4;
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(var1[137].call(var1[138].callCurrent(this), this))) {
            Object var3 = var1[139].callStatic(HashCodeHelper.class, _result, var1[140].callCurrent(this));
            _result = var3;
        }

        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(var1[147].call(this.isMandatory(), this))) {
                Object var6 = var1[148].callStatic(HashCodeHelper.class, _result, this.isMandatory());
                _result = var6;
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(var1[143].call(var1[144].callCurrent(this), this))) {
            Object var5 = var1[145].callStatic(HashCodeHelper.class, _result, var1[146].callCurrent(this));
            _result = var5;
        }

        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(var1[153].call(this.getResult(), this))) {
                Object var8 = var1[154].callStatic(HashCodeHelper.class, _result, this.getResult());
                _result = var8;
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(var1[149].call(var1[150].callCurrent(this), this))) {
            Object var7 = var1[151].callStatic(HashCodeHelper.class, _result, var1[152].callCurrent(this));
            _result = var7;
        }

        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(var1[159].call(this.getCommonData(), this))) {
                Object var10 = var1[160].callStatic(HashCodeHelper.class, _result, this.getCommonData());
                _result = var10;
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(var1[155].call(var1[156].callCurrent(this), this))) {
            Object var9 = var1[157].callStatic(HashCodeHelper.class, _result, var1[158].callCurrent(this));
            _result = var9;
        }

        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(var1[165].call(this.getSecureToken(), this))) {
                Object var12 = var1[166].callStatic(HashCodeHelper.class, _result, this.getSecureToken());
                _result = var12;
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(var1[161].call(var1[162].callCurrent(this), this))) {
            Object var11 = var1[163].callStatic(HashCodeHelper.class, _result, var1[164].callCurrent(this));
            _result = var11;
        }

        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(var1[171].call(this.getErrorMessageCodeList(), this))) {
                Object var14 = var1[172].callStatic(HashCodeHelper.class, _result, this.getErrorMessageCodeList());
                _result = var14;
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(var1[167].call(var1[168].callCurrent(this), this))) {
            Object var13 = var1[169].callStatic(HashCodeHelper.class, _result, var1[170].callCurrent(this));
            _result = var13;
        }

        if (!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
            if (!DefaultTypeTransformation.booleanUnbox(var1[177].call(this.getErrorMessageTextList(), this))) {
                Object var16 = var1[178].callStatic(HashCodeHelper.class, _result, this.getErrorMessageTextList());
                _result = var16;
            }
        } else if (!DefaultTypeTransformation.booleanUnbox(var1[173].call(var1[174].callCurrent(this), this))) {
            Object var15 = var1[175].callStatic(HashCodeHelper.class, _result, var1[176].callCurrent(this));
            _result = var15;
        }

        return DefaultTypeTransformation.intUnbox(_result);
    }

    public boolean canEqual(Object other) {
        CallSite[] var2 = $getCallSiteArray();
        return other instanceof ServiceResult;
    }

    public boolean equals(Object other) {
        CallSite[] var2 = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareEqual(other, (Object) null)) {
            return false;
        } else if (DefaultTypeTransformation.booleanUnbox(var2[179].callCurrent(this, other))) {
            return true;
        } else if (!(other instanceof ServiceResult)) {
            return false;
        } else {
            ServiceResult otherTyped = (ServiceResult) other;
            if (!DefaultTypeTransformation.booleanUnbox(var2[180].call(otherTyped, this))) {
                return false;
            } else if (!ScriptBytecodeAdapter.compareEqual(var2[181].callCurrent(this), var2[182].call(otherTyped))) {
                return false;
            } else if (!ScriptBytecodeAdapter.compareEqual(var2[183].callCurrent(this), var2[184].call(otherTyped))) {
                return false;
            } else if (!ScriptBytecodeAdapter.compareEqual(var2[185].callCurrent(this), var2[186].call(otherTyped))) {
                return false;
            } else if (!ScriptBytecodeAdapter.compareEqual(var2[187].callCurrent(this), var2[188].call(otherTyped))) {
                return false;
            } else if (!ScriptBytecodeAdapter.compareEqual(var2[189].callCurrent(this), var2[190].call(otherTyped))) {
                return false;
            } else if (!ScriptBytecodeAdapter.compareEqual(var2[191].callCurrent(this), var2[192].call(otherTyped))) {
                return false;
            } else {
                return ScriptBytecodeAdapter.compareEqual(var2[193].callCurrent(this), var2[194].call(otherTyped));
            }
        }
    }

/*    public ServiceResult mergeWith(ServiceResult other, List resultNameList) {
        CallSite[] var3 = $getCallSiteArray();
        return this.mergeWith(other, resultNameList, (List) ScriptBytecodeAdapter.createList(new Object[]{"commonDataA", "commonDataB"}));
    }

    public ServiceResult mergeWith(ServiceResult other) {
        CallSite[] var2 = $getCallSiteArray();
        return this.mergeWith(other, (List) ScriptBytecodeAdapter.createList(new Object[]{"resultA", "resultB"}), (List) ScriptBytecodeAdapter.createList(new Object[]{"commonDataA", "commonDataB"}));
    }*/

    public static final long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean var1) {
        this.success = var1;
    }

    public boolean getMandatory() {
        return this.mandatory;
    }

    public boolean isMandatory() {
        return this.mandatory;
    }

    public void setMandatory(boolean var1) {
        this.mandatory = var1;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object var1) {
        this.result = var1;
    }

    public Object getCommonData() {
        return this.commonData;
    }

    public void setCommonData(Object var1) {
        this.commonData = var1;
    }

    public String getSecureToken() {
        return this.secureToken;
    }

    public void setSecureToken(String var1) {
        this.secureToken = var1;
    }

    public List<String> getErrorMessageCodeList() {
        return this.errorMessageCodeList;
    }

    public void setErrorMessageCodeList(List<String> var1) {
        this.errorMessageCodeList = var1;
    }

    public List<String> getErrorMessageTextList() {
        return this.errorMessageTextList;
    }

    public void setErrorMessageTextList(List<String> var1) {
        this.errorMessageTextList = var1;
    }
}
