#include "falling_edge_interface.h"
const int  rt_version = Srtv62;

const char* _SCSIM_CheckSum = "b4e7ad214a47fcb478e356e467806109";
const char* _SCSIM_SmuTypesCheckSum = "e593b24d3a50553941967dd1b837608e";

/*******************************
 * Validity
 *******************************/
int valid(void * pHandle) {
	return 1;
}
int notvalid(void * pHandle) {
	return 0;
}

/*******************************
 * Simulation context
 *******************************/
inC_falling_edge inputs_ctx;
static inC_falling_edge inputs_ctx_restore;
static inC_falling_edge inputs_ctx_execute;
outC_falling_edge outputs_ctx;
static outC_falling_edge outputs_ctx_restore;

static void _SCSIM_RestoreInterface(void) {
	inputs_ctx.X = inputs_ctx_restore.X;
	outputs_ctx = outputs_ctx_restore;
}

static void _SCSIM_ExecuteInterface(void) {
	pSimulator->m_pfnAcquireValueMutex(pSimulator);
	inputs_ctx_execute.X = inputs_ctx.X;
	pSimulator->m_pfnReleaseValueMutex(pSimulator);
}

extern void _SCSIM_Mapping_Create();

/*******************************
 * Cyclic function encapsulation
 *******************************/
void SimInit(void) {
	/* Context initialization */
	_SCSIM_Mapping_Create();
	_SCSIM_RestoreInterface();
#ifdef EXTENDED_SIM
	BeforeSimInit();
#endif /* EXTENDED_SIM */
	falling_edge_reset(&outputs_ctx);
#ifdef EXTENDED_SIM
	AfterSimInit();
#endif /* EXTENDED_SIM */
}

#ifdef EXTENDED_SIM
int GraphicalInputsConnected = 1;
#endif /* EXTENDED_SIM */
int SimStep(void) {
#ifdef EXTENDED_SIM
	if (GraphicalInputsConnected)
		BeforeSimStep();
#endif /* EXTENDED_SIM */
	_SCSIM_ExecuteInterface();
	falling_edge(&inputs_ctx_execute, &outputs_ctx);
#ifdef EXTENDED_SIM
	AfterSimStep();
#endif /* EXTENDED_SIM */
	return 1;
}

void SimStop(void) {
#ifdef EXTENDED_SIM
	ExtendedSimStop();
#endif /* EXTENDED_SIM */
}

int SsmGetDumpSize(void) {
	int nSize = 0;
	nSize += sizeof(inC_falling_edge);
	nSize += sizeof(outC_falling_edge);
#ifdef EXTENDED_SIM
	nSize += ExtendedGetDumpSize();
#endif /* EXTENDED_SIM */
	return nSize;
}

void SsmGatherDumpData(char * pData) {
	char* pCurrent = pData;
	memcpy(pCurrent, &inputs_ctx, sizeof(inC_falling_edge));
	pCurrent += sizeof(inC_falling_edge);
	memcpy(pCurrent, &outputs_ctx, sizeof(outC_falling_edge));
	pCurrent += sizeof(outC_falling_edge);
#ifdef EXTENDED_SIM
	ExtendedGatherDumpData(pCurrent);
#endif /* EXTENDED_SIM */
}

void SsmRestoreDumpData(const char * pData) {
	const char* pCurrent = pData;
	memcpy(&inputs_ctx, pCurrent, sizeof(inC_falling_edge));
	pCurrent += sizeof(inC_falling_edge);
	memcpy(&outputs_ctx, pCurrent, sizeof(outC_falling_edge));
	pCurrent += sizeof(outC_falling_edge);
#ifdef EXTENDED_SIM
	ExtendedRestoreDumpData(pCurrent);
#endif /* EXTENDED_SIM */
}

const char * SsmGetCheckSum() {
	return _SCSIM_CheckSum;
}

const char * SsmGetSmuTypesCheckSum() {
	return _SCSIM_SmuTypesCheckSum;
}

void SsmUpdateValues(void) {
#ifdef EXTENDED_SIM
	UpdateValues();
#endif /* EXTENDED_SIM */
}

void SsmConnectExternalInputs(int bConnect) {
#ifdef EXTENDED_SIM
	GraphicalInputsConnected = bConnect;
#endif /* EXTENDED_SIM */
}

