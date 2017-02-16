#ifndef COUNTER3_INTERFACE
#define COUNTER3_INTERFACE


#include "SmuTypes.h"
#include "kcg_types.h"
#include "counter3.h"

extern ScSimulator * pSimulator;

/*******************************
 * Simulation context
 *******************************/
extern inC_counter3 inputs_ctx;
extern outC_counter3 outputs_ctx;
/*******************************
 * Validity
 *******************************/
extern int valid(void*);
extern int notvalid(void*);

#ifdef EXTENDED_SIM
void BeforeSimInit();
void AfterSimInit();
void BeforeSimStep();
void AfterSimStep();
void ExtendedSimStop();
void ExtendedGatherDumpData(char * pData);
void ExtendedRestoreDumpData(const char * pData);
int ExtendedGetDumpSize();
void UpdateValues();
extern void UpdateSimulatorValues();
extern int GraphicalInputsConnected;
#endif /* EXTENDED_SIM */


#endif /*COUNTER3_INTERFACE */
