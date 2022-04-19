/*
&generator.GenOperation{GenCommon:generator.GenCommon{Copyright:"", TargetImportPath:"tf-provider-example"}, Package:"device", ReceiverName:"o", Name:"updateDevice", Summary:"update a device", Description:"", Method:"PUT", Path:"/device/devices/{id}", BasePath:"/santaba/rest", Tags:[]string{"Device"}, UseTags:true, RootPackage:"restapi", Imports:map[string]string{"client":"tf-provider-example/client"}, DefaultImports:map[string]string{"device":"tf-provider-example/client/device", "models":"tf-provider-example/models"}, ExtraSchemas:generator.GenSchemaList{}, PackageAlias:"device", Authorized:true, Security:[]generator.GenSecurityRequirements{generator.GenSecurityRequirements{generator.GenSecurityRequirement{Name:"LMv1", Scopes:[]string{}}}}, SecurityDefinitions:generator.GenSecuritySchemes{generator.GenSecurityScheme{AppName:"updateDevice", ID:"LMv1", Name:"Authorization", ReceiverName:"o", IsBasicAuth:false, IsAPIKeyAuth:true, IsOAuth2:false, Scopes:[]string{}, Source:"header", Principal:"interface{}", PrincipalIsNullable:false, Description:"", Type:"apikey", In:"header", Flow:"", AuthorizationURL:"", TokenURL:"", Extensions:map[string]interface {}(nil), ScopesDesc:[]generator.GenSecurityScope{}}}, SecurityRequirements:[]analysis.SecurityRequirement{analysis.SecurityRequirement{Name:"LMv1", Scopes:[]string{}}}, Principal:"interface{}", PrincipalIsNullable:false, SuccessResponse:(*generator.GenResponse)(0xc0007d4780), SuccessResponses:[]generator.GenResponse{generator.GenResponse{Package:"device", ModelsPackage:"models", ReceiverName:"o", Name:"updateDeviceOK", Description:"successful operation", IsSuccess:true, Code:200, Method:"PUT", Path:"/device/devices/{id}", Headers:generator.GenHeaders(nil), Schema:(*generator.GenSchema)(0xc000322700), AllowsForStreaming:false, Imports:map[string]string{"client":"tf-provider-example/client"}, DefaultImports:map[string]string{"device":"tf-provider-example/client/device", "models":"tf-provider-example/models"}, Extensions:map[string]interface {}(nil), StrictResponders:false, OperationName:"updateDevice", Examples:generator.GenResponseExamples{}}}, Responses:generator.GenStatusCodeResponses{generator.GenResponse{Package:"device", ModelsPackage:"models", ReceiverName:"o", Name:"updateDeviceOK", Description:"successful operation", IsSuccess:true, Code:200, Method:"PUT", Path:"/device/devices/{id}", Headers:generator.GenHeaders(nil), Schema:(*generator.GenSchema)(0xc000322700), AllowsForStreaming:false, Imports:map[string]string{"client":"tf-provider-example/client"}, DefaultImports:map[string]string{"device":"tf-provider-example/client/device", "models":"tf-provider-example/models"}, Extensions:map[string]interface {}(nil), StrictResponders:false, OperationName:"updateDevice", Examples:generator.GenResponseExamples{}}}, DefaultResponse:(*generator.GenResponse)(0xc0007d44b0), Params:generator.GenParameters{generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:false, IsCustomFormatter:false, IsAliased:false, IsNullable:true, IsStream:false, IsEmptyOmitted:true, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:true, IsBaseType:false, HasDiscriminator:false, GoType:"models.Device", Pkg:"device", PkgAlias:"", AliasedType:"", SwaggerType:"object", SwaggerFormat:"", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:true, Required:true, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"Body", Name:"body", ModelsPackage:"models", Path:"\"body\"", ValueExpression:"o.Body", IndexVar:"i", KeyVar:"k", ReceiverName:"o", Location:"body", Title:"", Description:"", Converter:"", Formatter:"", Schema:(*generator.GenSchema)(0xc000322380), CollectionFormat:"", Child:(*generator.GenItems)(0xc000991600), Parent:(*generator.GenItems)(nil), Default:interface {}(nil), HasDefault:false, ZeroValue:"new(models.Device)", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:true, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}, generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:true, IsCustomFormatter:false, IsAliased:false, IsNullable:true, IsStream:false, IsEmptyOmitted:false, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:false, IsBaseType:false, HasDiscriminator:false, GoType:"int64", Pkg:"", PkgAlias:"", AliasedType:"", SwaggerType:"integer", SwaggerFormat:"int64", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:false, Required:false, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"End", Name:"end", ModelsPackage:"models", Path:"\"end\"", ValueExpression:"o.End", IndexVar:"i", KeyVar:"", ReceiverName:"o", Location:"query", Title:"", Description:"", Converter:"swag.ConvertInt64", Formatter:"swag.FormatInt64", Schema:(*generator.GenSchema)(nil), CollectionFormat:"", Child:(*generator.GenItems)(nil), Parent:(*generator.GenItems)(nil), Default:interface {}(nil), HasDefault:false, ZeroValue:"0", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:false, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}, generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:true, IsCustomFormatter:false, IsAliased:false, IsNullable:false, IsStream:false, IsEmptyOmitted:false, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:false, IsBaseType:false, HasDiscriminator:false, GoType:"int32", Pkg:"", PkgAlias:"", AliasedType:"", SwaggerType:"integer", SwaggerFormat:"int32", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:false, Required:true, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"ID", Name:"id", ModelsPackage:"models", Path:"\"id\"", ValueExpression:"o.ID", IndexVar:"i", KeyVar:"", ReceiverName:"o", Location:"path", Title:"", Description:"", Converter:"swag.ConvertInt32", Formatter:"swag.FormatInt32", Schema:(*generator.GenSchema)(nil), CollectionFormat:"", Child:(*generator.GenItems)(nil), Parent:(*generator.GenItems)(nil), Default:interface {}(nil), HasDefault:false, ZeroValue:"0", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:false, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}, generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:true, IsCustomFormatter:false, IsAliased:false, IsNullable:true, IsStream:false, IsEmptyOmitted:false, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:false, IsBaseType:false, HasDiscriminator:false, GoType:"string", Pkg:"", PkgAlias:"", AliasedType:"", SwaggerType:"string", SwaggerFormat:"", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:false, Required:false, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"NetflowFilter", Name:"netflowFilter", ModelsPackage:"models", Path:"\"netflowFilter\"", ValueExpression:"o.NetflowFilter", IndexVar:"i", KeyVar:"", ReceiverName:"o", Location:"query", Title:"", Description:"", Converter:"", Formatter:"", Schema:(*generator.GenSchema)(nil), CollectionFormat:"", Child:(*generator.GenItems)(nil), Parent:(*generator.GenItems)(nil), Default:interface {}(nil), HasDefault:false, ZeroValue:"\"\"", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:false, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}, generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:true, IsCustomFormatter:false, IsAliased:false, IsNullable:true, IsStream:false, IsEmptyOmitted:false, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:false, IsBaseType:false, HasDiscriminator:false, GoType:"string", Pkg:"", PkgAlias:"", AliasedType:"", SwaggerType:"string", SwaggerFormat:"", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:false, Required:false, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"OpType", Name:"opType", ModelsPackage:"models", Path:"\"opType\"", ValueExpression:"o.OpType", IndexVar:"i", KeyVar:"", ReceiverName:"o", Location:"query", Title:"", Description:"", Converter:"", Formatter:"", Schema:(*generator.GenSchema)(nil), CollectionFormat:"", Child:(*generator.GenItems)(nil), Parent:(*generator.GenItems)(nil), Default:"refresh", HasDefault:true, ZeroValue:"\"\"", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:false, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}, generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:true, IsCustomFormatter:false, IsAliased:false, IsNullable:true, IsStream:false, IsEmptyOmitted:false, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:false, IsBaseType:false, HasDiscriminator:false, GoType:"int64", Pkg:"", PkgAlias:"", AliasedType:"", SwaggerType:"integer", SwaggerFormat:"int64", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:false, Required:false, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"Start", Name:"start", ModelsPackage:"models", Path:"\"start\"", ValueExpression:"o.Start", IndexVar:"i", KeyVar:"", ReceiverName:"o", Location:"query", Title:"", Description:"", Converter:"swag.ConvertInt64", Formatter:"swag.FormatInt64", Schema:(*generator.GenSchema)(nil), CollectionFormat:"", Child:(*generator.GenItems)(nil), Parent:(*generator.GenItems)(nil), Default:interface {}(nil), HasDefault:false, ZeroValue:"0", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:false, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}}, QueryParams:generator.GenParameters{generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:true, IsCustomFormatter:false, IsAliased:false, IsNullable:true, IsStream:false, IsEmptyOmitted:false, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:false, IsBaseType:false, HasDiscriminator:false, GoType:"int64", Pkg:"", PkgAlias:"", AliasedType:"", SwaggerType:"integer", SwaggerFormat:"int64", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:false, Required:false, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"End", Name:"end", ModelsPackage:"models", Path:"\"end\"", ValueExpression:"o.End", IndexVar:"i", KeyVar:"", ReceiverName:"o", Location:"query", Title:"", Description:"", Converter:"swag.ConvertInt64", Formatter:"swag.FormatInt64", Schema:(*generator.GenSchema)(nil), CollectionFormat:"", Child:(*generator.GenItems)(nil), Parent:(*generator.GenItems)(nil), Default:interface {}(nil), HasDefault:false, ZeroValue:"0", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:false, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}, generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:true, IsCustomFormatter:false, IsAliased:false, IsNullable:true, IsStream:false, IsEmptyOmitted:false, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:false, IsBaseType:false, HasDiscriminator:false, GoType:"string", Pkg:"", PkgAlias:"", AliasedType:"", SwaggerType:"string", SwaggerFormat:"", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:false, Required:false, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"NetflowFilter", Name:"netflowFilter", ModelsPackage:"models", Path:"\"netflowFilter\"", ValueExpression:"o.NetflowFilter", IndexVar:"i", KeyVar:"", ReceiverName:"o", Location:"query", Title:"", Description:"", Converter:"", Formatter:"", Schema:(*generator.GenSchema)(nil), CollectionFormat:"", Child:(*generator.GenItems)(nil), Parent:(*generator.GenItems)(nil), Default:interface {}(nil), HasDefault:false, ZeroValue:"\"\"", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:false, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}, generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:true, IsCustomFormatter:false, IsAliased:false, IsNullable:true, IsStream:false, IsEmptyOmitted:false, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:false, IsBaseType:false, HasDiscriminator:false, GoType:"string", Pkg:"", PkgAlias:"", AliasedType:"", SwaggerType:"string", SwaggerFormat:"", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:false, Required:false, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"OpType", Name:"opType", ModelsPackage:"models", Path:"\"opType\"", ValueExpression:"o.OpType", IndexVar:"i", KeyVar:"", ReceiverName:"o", Location:"query", Title:"", Description:"", Converter:"", Formatter:"", Schema:(*generator.GenSchema)(nil), CollectionFormat:"", Child:(*generator.GenItems)(nil), Parent:(*generator.GenItems)(nil), Default:"refresh", HasDefault:true, ZeroValue:"\"\"", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:false, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}, generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:true, IsCustomFormatter:false, IsAliased:false, IsNullable:true, IsStream:false, IsEmptyOmitted:false, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:false, IsBaseType:false, HasDiscriminator:false, GoType:"int64", Pkg:"", PkgAlias:"", AliasedType:"", SwaggerType:"integer", SwaggerFormat:"int64", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:false, Required:false, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"Start", Name:"start", ModelsPackage:"models", Path:"\"start\"", ValueExpression:"o.Start", IndexVar:"i", KeyVar:"", ReceiverName:"o", Location:"query", Title:"", Description:"", Converter:"swag.ConvertInt64", Formatter:"swag.FormatInt64", Schema:(*generator.GenSchema)(nil), CollectionFormat:"", Child:(*generator.GenItems)(nil), Parent:(*generator.GenItems)(nil), Default:interface {}(nil), HasDefault:false, ZeroValue:"0", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:false, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}}, PathParams:generator.GenParameters{generator.GenParameter{resolvedType:generator.resolvedType{IsAnonymous:false, IsArray:false, IsMap:false, IsInterface:false, IsPrimitive:true, IsCustomFormatter:false, IsAliased:false, IsNullable:false, IsStream:false, IsEmptyOmitted:false, IsJSONString:false, IsEnumCI:false, IsBase64:false, IsExternal:false, IsTuple:false, HasAdditionalItems:false, IsComplexObject:false, IsBaseType:false, HasDiscriminator:false, GoType:"int32", Pkg:"", PkgAlias:"", AliasedType:"", SwaggerType:"integer", SwaggerFormat:"int32", Extensions:spec.Extensions(nil), ElemType:(*generator.resolvedType)(nil), IsMapNullOverride:false, IsSuperAlias:false, IsEmbedded:false, SkipExternalValidation:false}, sharedValidations:generator.sharedValidations{SchemaValidations:spec.SchemaValidations{CommonValidations:spec.CommonValidations{Maximum:(*float64)(nil), ExclusiveMaximum:false, Minimum:(*float64)(nil), ExclusiveMinimum:false, MaxLength:(*int64)(nil), MinLength:(*int64)(nil), Pattern:"", MaxItems:(*int64)(nil), MinItems:(*int64)(nil), UniqueItems:false, MultipleOf:(*float64)(nil), Enum:[]interface {}(nil)}, PatternProperties:spec.SchemaProperties(nil), MaxProperties:(*int64)(nil), MinProperties:(*int64)(nil)}, HasValidations:false, HasContextValidations:false, Required:true, HasSliceValidations:false, ItemsEnum:[]interface {}(nil)}, ID:"ID", Name:"id", ModelsPackage:"models", Path:"\"id\"", ValueExpression:"o.ID", IndexVar:"i", KeyVar:"", ReceiverName:"o", Location:"path", Title:"", Description:"", Converter:"swag.ConvertInt32", Formatter:"swag.FormatInt32", Schema:(*generator.GenSchema)(nil), CollectionFormat:"", Child:(*generator.GenItems)(nil), Parent:(*generator.GenItems)(nil), Default:interface {}(nil), HasDefault:false, ZeroValue:"0", AllowEmptyValue:false, HasSimpleBodyParams:false, HasModelBodyParams:false, HasSimpleBodyItems:false, HasModelBodyItems:false, HasSimpleBodyMap:false, HasModelBodyMap:false, Extensions:map[string]interface {}(nil)}}, HeaderParams:generator.GenParameters(nil), FormParams:generator.GenParameters(nil), HasQueryParams:true, HasPathParams:true, HasHeaderParams:false, HasFormParams:false, HasFormValueParams:false, HasFileParams:false, HasBodyParams:true, HasStreamingResponse:false, Schemes:[]string{"https"}, ExtraSchemes:[]string{}, SchemeOverrides:[]string(nil), ExtraSchemeOverrides:[]string(nil), ProducesMediaTypes:[]string{"application/json"}, ConsumesMediaTypes:[]string{"application/json"}, TimeoutName:"timeout", Extensions:map[string]interface {}(nil), StrictResponders:false, ExternalDocs:(*spec.ExternalDocumentation)(nil), Produces:[]string{"application/json"}, Consumes:[]string{"application/json"}}
*/
