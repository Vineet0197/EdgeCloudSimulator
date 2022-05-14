function [] = plotAvgVmUtilization()

    plotGenericResult(2, 8, 'Average VM Utilization of Edge (%)', 'ALL_APPS', '');

    plotGenericResult(4, 8, 'Average VM Utilization of Mobile(%)', 'SMART_BUILDING', '');
end