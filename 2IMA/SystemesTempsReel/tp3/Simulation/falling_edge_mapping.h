#ifndef FALLING_EDGE_SCSIM_MAPPING
#define FALLING_EDGE_SCSIM_MAPPING

#include "SmuTypes.h"
#include "falling_edge_type.h"

void _SCSIM_Mapping_Create();
extern ControlUtils _SCSIM_BoolEntity_Control_Utils;
extern TypeUtils _SCSIM_kcg_real_Utils;
extern TypeUtils _SCSIM_kcg_bool_Utils;
extern TypeUtils _SCSIM_kcg_char_Utils;
extern TypeUtils _SCSIM_kcg_int_Utils;
#include "falling_edge.h"
void _SCSIM_Mapping_falling_edge();

void* _SCSIM_Get_falling_edge_Handle(void* pInstance, int nHandleIdent, int* pIteratorFilter, int nSize);


#endif /*FALLING_EDGE_SCSIM_MAPPING */
