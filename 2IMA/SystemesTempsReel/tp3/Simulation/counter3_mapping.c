#include "counter3_mapping.h"
#include "counter3_interface.h"
#include "kcg_sensors.h"

/****************************************************************
 ** Boolean entity activation
 ****************************************************************/
static ControlUtils _SCSIM_BoolEntity_Control_Utils = {_SCSIM_BoolEntity_is_active};
/****************************************************************
 ** Type utils declarations
 ****************************************************************/
TypeUtils _SCSIM_kcg_real_Utils = {kcg_real_to_string,
	check_kcg_real_string,
	string_to_kcg_real,
	is_kcg_real_allow_double_convertion,
	kcg_real_to_double,
	compare_kcg_real_type,
	get_kcg_real_signature,
	get_kcg_real_filter_utils,
	kcg_real_filter_size,
	kcg_real_filter_values};
TypeUtils _SCSIM_kcg_bool_Utils = {kcg_bool_to_string,
	check_kcg_bool_string,
	string_to_kcg_bool,
	is_kcg_bool_allow_double_convertion,
	kcg_bool_to_double,
	compare_kcg_bool_type,
	get_kcg_bool_signature,
	get_kcg_bool_filter_utils,
	kcg_bool_filter_size,
	kcg_bool_filter_values};
TypeUtils _SCSIM_kcg_char_Utils = {kcg_char_to_string,
	check_kcg_char_string,
	string_to_kcg_char,
	is_kcg_char_allow_double_convertion,
	kcg_char_to_double,
	compare_kcg_char_type,
	get_kcg_char_signature,
	get_kcg_char_filter_utils,
	kcg_char_filter_size,
	kcg_char_filter_values};
TypeUtils _SCSIM_kcg_int_Utils = {kcg_int_to_string,
	check_kcg_int_string,
	string_to_kcg_int,
	is_kcg_int_allow_double_convertion,
	kcg_int_to_double,
	compare_kcg_int_type,
	get_kcg_int_signature,
	get_kcg_int_filter_utils,
	kcg_int_filter_size,
	kcg_int_filter_values};

/****************************************************************
 ** Mapping creation function
 ****************************************************************/
void _SCSIM_Mapping_Create() {
	_SCSIM_Mapping_counter3();
	pSimulator->m_pfnFinalizeMapping(pSimulator);
}

/****************************************************************
 ** counter3/ mapping function
 ****************************************************************/
void _SCSIM_Mapping_counter3() {
	pSimulator->m_pfnSetRoot(pSimulator, "counter3/", &outputs_ctx, _SCSIM_Get_counter3_Handle);
	pSimulator->m_pfnAddLocal(pSimulator, "_L14", &_SCSIM_kcg_int_Utils, 1, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L15", &_SCSIM_kcg_int_Utils, 2, valid, 0, 0);
	_SCSIM_Mapping_counter2("counter2", "2", 3, 4, kcg_true);
	_SCSIM_Mapping_counter("counter", "2", 5, 6, kcg_true);
	pSimulator->m_pfnAddLocal(pSimulator, "_L10", &_SCSIM_kcg_bool_Utils, 7, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L11", &_SCSIM_kcg_bool_Utils, 8, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L13", &_SCSIM_kcg_bool_Utils, 9, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L16", &_SCSIM_kcg_bool_Utils, 10, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L17", &_SCSIM_kcg_bool_Utils, 11, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L18", &_SCSIM_kcg_bool_Utils, 12, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L19", &_SCSIM_kcg_int_Utils, 13, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L20", &_SCSIM_kcg_bool_Utils, 14, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L_kcg_clock", &_SCSIM_kcg_bool_Utils, 4, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L_kcg_clock", &_SCSIM_kcg_bool_Utils, 6, valid, 0, 0);
	pSimulator->m_pfnAddOutput(pSimulator, "n", &_SCSIM_kcg_int_Utils, 15, valid, 0, 0);
	pSimulator->m_pfnAddInput(pSimulator, "reset", &_SCSIM_kcg_bool_Utils, 16, valid, 0, 0);
	pSimulator->m_pfnAddInput(pSimulator, "c", &_SCSIM_kcg_bool_Utils, 17, valid, 0, 0);
}

void* _SCSIM_Get_counter3_Handle(void* pInstance, int nHandleIdent, int* pIteratorFilter, int nSize) {
	switch (nHandleIdent) {
		case 1:
			return &(outputs_ctx._L14);
		case 2:
			return &(outputs_ctx._L15);
		case 3:
			return &(outputs_ctx._1_Context_2);
		case 5:
			return &(outputs_ctx.Context_2);
		case 7:
			return &(outputs_ctx._L10);
		case 8:
			return &(outputs_ctx._L11);
		case 9:
			return &(outputs_ctx._L13);
		case 10:
			return &(outputs_ctx._L16);
		case 11:
			return &(outputs_ctx._L17);
		case 12:
			return &(outputs_ctx._L18);
		case 13:
			return &(outputs_ctx._L19);
		case 14:
			return &(outputs_ctx._L20);
		case 4:
			return &(outputs_ctx.tmp2);
		case 6:
			return &(outputs_ctx.tmp);
		case 15:
			return &(outputs_ctx.n);
		case 16:
			return &(inputs_ctx.reset);
		case 17:
			return &(inputs_ctx.c);
		default:
			break;
	}
	return 0;
}

/****************************************************************
 ** counter2/ mapping function
 ****************************************************************/
void _SCSIM_Mapping_counter2(const char* pszPath, const char* pszInstanceName, int nHandleIdent, int nClockHandleIdent, int clockVal) {
	pSimulator->m_pfnPushInstance(pSimulator, pszPath, pszInstanceName, nHandleIdent, _SCSIM_Get_counter2_Handle, nClockHandleIdent, clockVal);
	_SCSIM_Mapping_counter("counter", "1", 18, 0, 0);
	_SCSIM_Mapping_decounter("decounter", "1", 19, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L5", &_SCSIM_kcg_int_Utils, 20, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L9", &_SCSIM_kcg_bool_Utils, 21, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L10", &_SCSIM_kcg_int_Utils, 22, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L11", &_SCSIM_kcg_int_Utils, 23, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L12", &_SCSIM_kcg_bool_Utils, 24, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L13", &_SCSIM_kcg_bool_Utils, 25, valid, 0, 0);
	pSimulator->m_pfnAddOutput(pSimulator, "n", &_SCSIM_kcg_int_Utils, 26, valid, 0, 0);
	pSimulator->m_pfnPopInstance(pSimulator);
}

void* _SCSIM_Get_counter2_Handle(void* pInstance, int nHandleIdent, int* pIteratorFilter, int nSize) {
	outC_counter2* pContext = (outC_counter2*)pInstance;
	switch (nHandleIdent) {
		case 18:
			return &((*pContext)._1_Context_1);
		case 19:
			return &((*pContext).Context_1);
		case 20:
			return &((*pContext)._L5);
		case 21:
			return &((*pContext)._L9);
		case 22:
			return &((*pContext)._L10);
		case 23:
			return &((*pContext)._L11);
		case 24:
			return &((*pContext)._L12);
		case 25:
			return &((*pContext)._L13);
		case 26:
			return &((*pContext).n);
		default:
			break;
	}
	return 0;
}

/****************************************************************
 ** counter/ mapping function
 ****************************************************************/
void _SCSIM_Mapping_counter(const char* pszPath, const char* pszInstanceName, int nHandleIdent, int nClockHandleIdent, int clockVal) {
	pSimulator->m_pfnPushInstance(pSimulator, pszPath, pszInstanceName, nHandleIdent, _SCSIM_Get_counter_Handle, nClockHandleIdent, clockVal);
	pSimulator->m_pfnAddLocal(pSimulator, "_L1", &_SCSIM_kcg_int_Utils, 27, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L2", &_SCSIM_kcg_int_Utils, 28, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L3", &_SCSIM_kcg_bool_Utils, 29, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L4", &_SCSIM_kcg_int_Utils, 30, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L6", &_SCSIM_kcg_int_Utils, 31, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L7", &_SCSIM_kcg_int_Utils, 32, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L8", &_SCSIM_kcg_int_Utils, 33, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L9", &_SCSIM_kcg_int_Utils, 34, valid, 0, 0);
	pSimulator->m_pfnAddOutput(pSimulator, "n", &_SCSIM_kcg_int_Utils, 35, valid, 0, 0);
	pSimulator->m_pfnPopInstance(pSimulator);
}

void* _SCSIM_Get_counter_Handle(void* pInstance, int nHandleIdent, int* pIteratorFilter, int nSize) {
	outC_counter* pContext = (outC_counter*)pInstance;
	switch (nHandleIdent) {
		case 27:
			return &((*pContext)._L1);
		case 28:
			return &((*pContext)._L2);
		case 29:
			return &((*pContext)._L3);
		case 30:
			return &((*pContext)._L4);
		case 31:
			return &((*pContext)._L6);
		case 32:
			return &((*pContext)._L7);
		case 33:
			return &((*pContext)._L8);
		case 34:
			return &((*pContext)._L9);
		case 35:
			return &((*pContext).n);
		default:
			break;
	}
	return 0;
}

/****************************************************************
 ** decounter/ mapping function
 ****************************************************************/
void _SCSIM_Mapping_decounter(const char* pszPath, const char* pszInstanceName, int nHandleIdent, int nClockHandleIdent, int clockVal) {
	pSimulator->m_pfnPushInstance(pSimulator, pszPath, pszInstanceName, nHandleIdent, _SCSIM_Get_decounter_Handle, nClockHandleIdent, clockVal);
	pSimulator->m_pfnAddLocal(pSimulator, "_L6", &_SCSIM_kcg_int_Utils, 36, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L8", &_SCSIM_kcg_int_Utils, 37, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L5", &_SCSIM_kcg_int_Utils, 38, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L4", &_SCSIM_kcg_bool_Utils, 39, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L2", &_SCSIM_kcg_int_Utils, 40, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L1", &_SCSIM_kcg_int_Utils, 41, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L9", &_SCSIM_kcg_int_Utils, 42, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L10", &_SCSIM_kcg_int_Utils, 43, valid, 0, 0);
	pSimulator->m_pfnAddOutput(pSimulator, "n", &_SCSIM_kcg_int_Utils, 44, valid, 0, 0);
	pSimulator->m_pfnPopInstance(pSimulator);
}

void* _SCSIM_Get_decounter_Handle(void* pInstance, int nHandleIdent, int* pIteratorFilter, int nSize) {
	outC_decounter* pContext = (outC_decounter*)pInstance;
	switch (nHandleIdent) {
		case 36:
			return &((*pContext)._L6);
		case 37:
			return &((*pContext)._L8);
		case 38:
			return &((*pContext)._L5);
		case 39:
			return &((*pContext)._L4);
		case 40:
			return &((*pContext)._L2);
		case 41:
			return &((*pContext)._L1);
		case 42:
			return &((*pContext)._L9);
		case 43:
			return &((*pContext)._L10);
		case 44:
			return &((*pContext).n);
		default:
			break;
	}
	return 0;
}

