#include "counter3_interface.h"
const int  rt_version = Srtv62;

const char* _SCSIM_CheckSum = "fb1ae2bfcc544679e4969d9cfaf0cad8";
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
inC_counter3 inputs_ctx;
static inC_counter3 inputs_ctx_restore;
static inC_counter3 inputs_ctx_execute;
outC_counter3 outputs_ctx;
static outC_counter3 outputs_ctx_restore;

static void _SCSIM_RestoreInterface(void) {
	inputs_ctx.reset = inputs_ctx_restore.reset;
	inputs_ctx.c = inputs_ctx_restore.c;
	outputs_ctx = outputs_ctx_restore;
}

static void _SCSIM_ExecuteInterface(void) {
	pSimulator->m_pfnAcquireValueMutex(pSimulator);
	inputs_ctx_execute.reset = inputs_ctx.reset;
	inputs_ctx_execute.c = inputs_ctx.c;
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
	counter3_reset(&outputs_ctx);
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
	counter3(&inputs_ctx_execute, &outputs_ctx);
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
	nSize += sizeof(inC_counter3);
	nSize += sizeof(outC_counter3);
#ifdef EXTENDED_SIM
	nSize += ExtendedGetDumpSize();
#endif /* EXTENDED_SIM */
	return nSize;
}

void SsmGatherDumpData(char * pData) {
	char* pCurrent = pData;
	memcpy(pCurrent, &inputs_ctx, sizeof(inC_counter3));
	pCurrent += sizeof(inC_counter3);
	memcpy(pCurrent, &outputs_ctx, sizeof(outC_counter3));
	pCurrent += sizeof(outC_counter3);
#ifdef EXTENDED_SIM
	ExtendedGatherDumpData(pCurrent);
#endif /* EXTENDED_SIM */
}

void SsmRestoreDumpData(const char * pData) {
	const char* pCurrent = pData;
	memcpy(&inputs_ctx, pCurrent, sizeof(inC_counter3));
	pCurrent += sizeof(inC_counter3);
	memcpy(&outputs_ctx, pCurrent, sizeof(outC_counter3));
	pCurrent += sizeof(outC_counter3);
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

