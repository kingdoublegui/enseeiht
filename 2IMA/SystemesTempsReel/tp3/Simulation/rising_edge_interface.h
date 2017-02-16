#ifndef RISING_EDGE_INTERFACE
#define RISING_EDGE_INTERFACE


#include "SmuTypes.h"
#include "kcg_types.h"
#include "rising_edge.h"

extern ScSimulator * pSimulator;

/*******************************
 * Simulation context
 *******************************/
extern inC_rising_edge inputs_ctx;
extern outC_rising_edge outputs_ctx;
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


#endif /*RISING_EDGE_INTERFACE */
