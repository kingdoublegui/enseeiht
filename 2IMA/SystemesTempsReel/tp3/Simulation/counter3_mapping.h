#ifndef COUNTER3_SCSIM_MAPPING
#define COUNTER3_SCSIM_MAPPING

#include "SmuTypes.h"
#include "counter3_type.h"

void _SCSIM_Mapping_Create();
extern ControlUtils _SCSIM_BoolEntity_Control_Utils;
extern TypeUtils _SCSIM_kcg_real_Utils;
extern TypeUtils _SCSIM_kcg_bool_Utils;
extern TypeUtils _SCSIM_kcg_char_Utils;
extern TypeUtils _SCSIM_kcg_int_Utils;
#include "counter3.h"
void _SCSIM_Mapping_counter3();

void* _SCSIM_Get_counter3_Handle(void* pInstance, int nHandleIdent, int* pIteratorFilter, int nSize);

#include "counter2.h"
void _SCSIM_Mapping_counter2(const char* pszPath, const char* pszInstanceName, int nHandleIdent, int nClockHandleIdent, int clockVal);
void* _SCSIM_Get_counter2_Handle(void* pInstance, int nHandleIdent, int* pIteratorFilter, int nSize);

#include "counter.h"
void _SCSIM_Mapping_counter(const char* pszPath, const char* pszInstanceName, int nHandleIdent, int nClockHandleIdent, int clockVal);
void* _SCSIM_Get_counter_Handle(void* pInstance, int nHandleIdent, int* pIteratorFilter, int nSize);

#include "decounter.h"
void _SCSIM_Mapping_decounter(const char* pszPath, const char* pszInstanceName, int nHandleIdent, int nClockHandleIdent, int clockVal);
void* _SCSIM_Get_decounter_Handle(void* pInstance, int nHandleIdent, int* pIteratorFilter, int nSize);


#endif /*COUNTER3_SCSIM_MAPPING */
