#include "rising_edge_mapping.h"
#include "rising_edge_interface.h"
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
	_SCSIM_Mapping_rising_edge();
	pSimulator->m_pfnFinalizeMapping(pSimulator);
}

/****************************************************************
 ** rising_edge/ mapping function
 ****************************************************************/
void _SCSIM_Mapping_rising_edge() {
	pSimulator->m_pfnSetRoot(pSimulator, "rising_edge/", &outputs_ctx, _SCSIM_Get_rising_edge_Handle);
	pSimulator->m_pfnAddLocal(pSimulator, "_L7", &_SCSIM_kcg_bool_Utils, 1, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L2", &_SCSIM_kcg_bool_Utils, 2, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L3", &_SCSIM_kcg_bool_Utils, 3, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L4", &_SCSIM_kcg_bool_Utils, 4, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L5", &_SCSIM_kcg_bool_Utils, 5, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L6", &_SCSIM_kcg_bool_Utils, 6, valid, 0, 0);
	pSimulator->m_pfnAddLocal(pSimulator, "_L8", &_SCSIM_kcg_bool_Utils, 7, valid, 0, 0);
	pSimulator->m_pfnAddOutput(pSimulator, "Y", &_SCSIM_kcg_bool_Utils, 8, valid, 0, 0);
	pSimulator->m_pfnAddInput(pSimulator, "X", &_SCSIM_kcg_bool_Utils, 9, valid, 0, 0);
}

void* _SCSIM_Get_rising_edge_Handle(void* pInstance, int nHandleIdent, int* pIteratorFilter, int nSize) {
	switch (nHandleIdent) {
		case 1:
			return &(outputs_ctx._L7);
		case 2:
			return &(outputs_ctx._L2);
		case 3:
			return &(outputs_ctx._L3);
		case 4:
			return &(outputs_ctx._L4);
		case 5:
			return &(outputs_ctx._L5);
		case 6:
			return &(outputs_ctx._L6);
		case 7:
			return &(outputs_ctx._L8);
		case 8:
			return &(outputs_ctx.Y);
		case 9:
			return &(inputs_ctx.X);
		default:
			break;
	}
	return 0;
}

